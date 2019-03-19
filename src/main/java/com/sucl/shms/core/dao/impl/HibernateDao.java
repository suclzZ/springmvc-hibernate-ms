package com.sucl.shms.core.dao.impl;

import com.sucl.shms.core.dao.Dao;
import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;
import com.sucl.shms.core.orm.annotation.Sort;
import com.sucl.shms.core.orm.hibernate.HibernateCondition;
import com.sucl.shms.core.orm.hibernate.HibernateOrCondition;
import com.sucl.shms.core.orm.hibernate.HibernateOrder;
import com.sucl.shms.core.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * dao公共实现方法
 *
 * session:
 *  sessionFactory.getCurrentSession();
 *  从当前线程中获取session，如果不存在则创建并放入线程。session关闭依赖事务，需要指定事务某事thread|jta
 *  sessionFactory.openSession();
 *  直接创建新的session，需要手动关闭session，在不同事务中，需要注意。
 * crud：
 *  save: 临时态数据持久化
 *  get: 从数据库查询，没有返回null
 *  load: 从缓存获取，没有则从数据库，数据库没有则异常
 *  update: 游离态数据持久化
 *  saveOrupdate: save(),update()
 *  merge: 先select然后update，同saveOrupdate。同时针对游离态、临时态数据进行持久化，使用merge
 *  delete: 持久化转变游离态
 *
 * @author sucl
 * @since 2019/3/16
 */
@Slf4j
public abstract class HibernateDao<T,PK extends Serializable> implements Dao<T,PK> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
//        return  sessionFactory.openSession();
    }

    protected abstract Class<T> getModelClazz();

    @Override
    public List<T> getAll() {
        Query query = getSession().createQuery("from " + getModelClazz().getName());
        return query.list();
    }

    @Override
    public List<T> getAll(Collection<Condition> conditions) {
        DetachedCriteria detachedCriteria = getDetachedCriteria(getModelClazz());
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        Object[] result = generateQueryExpression(conditions, criteria);
        criteria = (Criteria) result[0];
        return criteria.list();
    }

    protected DetachedCriteria getDetachedCriteria(Class<?> clazz) {
        return DetachedCriteria.forClass(clazz);
    }

    @Override
    public Pager<T> getPager(Pager pager, Collection<Condition> conditions, Collection<Order> orders) {
        pager = pager!=null? pager: new Pager();
        int pageType = pager.getPageType(),totalCount=0;
        List<T> list = new ArrayList();
        DetachedCriteria detachedCriteria = getDetachedCriteria(getModelClazz());
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        Object[] result = generateQueryExpression(conditions, criteria);// criteria、aliases
        criteria = (Criteria)result[0];
        if (pageType == 0 || pageType == 2) {//查总数
            try {
                Object res = criteria.setProjection(Projections.rowCount()).uniqueResult();
                totalCount = Integer.valueOf(res.toString()).intValue();
            }catch (RuntimeException localRuntimeException) {
            }
            criteria.setProjection(null);
            pager.setTotleCount(totalCount);
        }
        if (pageType == 0 || pageType == 1) {//查结果（带排序）
            criteria = generateOrderExpression(orders, result);
            if (pager.getPageSize() != -1)
                criteria = criteria.setFirstResult(pager.getPageStart()).setMaxResults(pager.getPageSize());
            list = criteria.list();
        }
        pager.setRecords(list);
        return pager;
    }

    protected Object[] generateQueryExpression(Collection<Condition> conditions, Criteria criteria){
        Object[] result = new Object[2];

        if (CollectionUtils.isEmpty(conditions)) {
            result[0] = criteria;
            return result;
        }

        Set aliasSet = new HashSet();
        for (Condition condition : conditions) {
            if (condition == null) continue;
            criteria = processCondition(criteria, condition, aliasSet);
        }
        result[0] = criteria;
        result[1] = aliasSet;
        return result;
    }

    private Criteria processCondition(Criteria criteria, Condition condition, Set<String> aliasSet){
        if ((condition instanceof HibernateCondition)){
            processHibernateCondition(criteria, (HibernateCondition)condition, aliasSet);
        }if ((condition instanceof HibernateOrCondition)){
            criteria = criteria.add(((HibernateOrCondition)condition).generateExpression());
            criteria = processHibernateOrConditionAlias(criteria, (HibernateOrCondition)condition, aliasSet);
        }
        return criteria;
    }

    private Criteria processHibernateOrConditionAlias(Criteria criteria, HibernateOrCondition condition, Set<String> aliasSet){
        criteria = processHibernateOrConditionAlias(criteria, condition.getCondition1(), aliasSet);
        criteria = processHibernateOrConditionAlias(criteria, condition.getCondition2(), aliasSet);
        return criteria;
    }

    private Criteria processHibernateOrConditionAlias(Criteria criteria, Condition cond, Set<String> aliasSet){
        if ((cond instanceof HibernateCondition))
            criteria = processAlias(criteria, cond.getProperty(), aliasSet);
        else if ((cond instanceof HibernateOrCondition)) {
            criteria = processHibernateOrConditionAlias(criteria, (HibernateOrCondition)cond, aliasSet);
        }
        return criteria;
    }

    private Criteria processHibernateCondition(Criteria criteria, HibernateCondition condition, Set<String> aliasSet){
        Criterion conditionCriterion = condition.generateExpression();
        if (conditionCriterion != null) {
            criteria = processAlias(criteria, condition.getProperty(), aliasSet);
            criteria = criteria.add(conditionCriterion);
        }
        return criteria;
    }

    private Criteria processAlias(Criteria criteria, String propertyName, Set<String> aliasSet){
        if (propertyName.indexOf(".") != -1) {
            String alias = propertyName.split("\\.")[0];
            if (!aliasSet.contains(alias)) {
                criteria = criteria.createAlias(alias, alias);
                aliasSet.add(alias);
            }
            alias = null;
        }
        return criteria;
    }

    private Criteria generateOrderExpression(Collection<Order> orders, Object[] result) throws HibernateException{
        Criteria criteria = (Criteria)result[0];
        Set<String> idProperties;
        if ((orders == null) || (orders.size() == 0)) {
            orders = new ArrayList();
            List<Method> sortGetterMethods = ReflectionUtils.annotationedGetterMethod(getModelClazz(), Sort.class);
            List<Field> sortAnnotationedFields = ReflectionUtils.annotationedField(getModelClazz(), Sort.class);

            for (Method method : sortGetterMethods) {
                Sort sort = method.getAnnotation(Sort.class);
                orders.add(new HibernateOrder(method.getName(),sort.direct()));
            }

            for (Field field : sortAnnotationedFields) {
                Sort sort = field.getAnnotation(Sort.class);
                orders.add(new HibernateOrder(field.getName(),sort.direct()));
            }
        }
        Set aliasSet = new HashSet();
        if (result[1] != null) {
            aliasSet = (Set)result[1];
        }

        for (Order order : orders) {
            String propertyName = order.getProperty();
            if (propertyName.indexOf(".") != -1) {
                String alias = propertyName.split("\\.")[0];
                if (!aliasSet.contains(alias)) {
                    criteria = criteria.createAlias(alias, alias);
                    aliasSet.add(alias);
                }
            }
            criteria.addOrder((org.hibernate.criterion.Order)order);
        }
        return criteria;
    }

    @Override
    public T get(PK id) {
        return (T) getSession().get(getModelClazz(),id);
    }

    @Override
    public T getInitializeObject(PK id, String[] props) {
        T t = (T)getSession().get(getModelClazz(),id);
        initializeObjectCollections(t, props);
        return t;
    }

    private void initializeObjectCollections(T initializeObject, String[] props) {
        if(props!=null){
            for(String prop : props){
                initializeObjectCollection(initializeObject, prop);
            }
        }
    }

    private void initializeObjectCollection(T initializeObject, String prop) {
        try {
            if(StringUtils.isNotEmpty(prop)){
                PropertyUtils.getProperty(initializeObject,prop);
                Hibernate.initialize(initializeObject);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exist(PK id) {
        return get(id)!=null;
    }

    @Override
    public boolean exist(String property, String value) {
        return getUniqueObjectByProperty(property,value)!=null;
    }

    private T getUniqueObjectByProperty(String property, String value) {
        Session session = getSession();
        String hql = "from "+getModelClazz().getName()+" as model where mode."+property + "=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,value);
        List<T> result = query.list();
        if(CollectionUtils.isEmpty(result)){
            log.warn("根据属性【{}】没有查询到任何数据！",property);
            return null;
        }
        return result.get(0);
    }

    @Override
    public T save(T t) {
        return (T)getSession().merge(t);
    }

    @Override
    public int save(List<T> ts) {
        getSession().saveOrUpdate(ts);
        return ts.size();
    }

    @Override
    public void remove(PK id) {
        getSession().delete(get(id));
    }

    @Override
    public void remove(String property, Object value) {
        getSession().delete(property,value);
    }

    @Override
    public void removeAll(Collection<Condition> conditions) {
        List<T> result = getAll(conditions);
        if(CollectionUtils.isNotEmpty(result))
            result.stream().forEach(e->{
                getSession().delete(e);
            });
    }
}
