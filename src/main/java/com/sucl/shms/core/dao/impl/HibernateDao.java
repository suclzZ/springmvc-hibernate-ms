package com.sucl.shms.core.dao.impl;

import com.sucl.shms.core.dao.Dao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Slf4j
public abstract class HibernateDao<T,PK extends Serializable> implements Dao<T,PK> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * getCurrentSession: 事务关闭自动关闭session，session绑定到当前线程，需要指定事务某事thread|jta
     * openSession(): 需要手动关闭
     * @return
     */
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
    public T get(PK id) {
        return (T) getSession().get(getModelClazz(),id);
    }

    @Override
    public boolean exist(PK id) {
        return get(id)!=null;
    }

    @Override
    public boolean exist(String property, String value) {
        return false;
    }

    @Override
    public T save(T t) {
        getSession().saveOrUpdate(t);
        return t;
    }

    @Override
    public int save(List<T> ts) {
        //
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
}
