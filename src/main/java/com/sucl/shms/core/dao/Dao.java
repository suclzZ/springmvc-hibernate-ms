package com.sucl.shms.core.dao;

import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
public interface Dao<T,PK extends Serializable> {

    /**
     * 查询所有
     * @return
     */
    List<T> getAll();

    /**
     * 根据条件查询
     * @param conditions
     * @return
     */
    List<T> getAll(Collection<Condition> conditions);

    /**
     * 根据分页、查询、排序条件查询分页数据
     * @param pager
     * @param conditions
     * @param orders
     * @return
     */
    Pager<T> getPager(Pager pager,Collection<Condition> conditions,Collection<Order> orders);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T get(PK id);

    /**
     * 即时加载指定属性，针对延迟加载的属性
     * @param id
     * @param props
     * @return
     */
    T getInitializeObject(PK id,String [] props);

    /**
     * 根据id查看是否存在
     * @param id
     * @return
     */
    boolean exist(PK id);

    /**
     * 根据指定属性查看是否存在
     * @param property
     * @param value
     * @return
     */
    boolean exist(String property,String value);

    /**
     * 保存或更新，根据id判断，id已存在则更新，否则新增
     * 如果id存在但数据库没有对应的数据，异常
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 批量保存
     * @param ts
     * @return
     */
    int save(List<T> ts);

    /**
     * 根据主键删除
     * @param id
     */
    void remove(PK id);

    /**
     * 根据指定属性删除
     * @param property
     * @param value
     */
    void remove(String property,Object value);

    /**
     * 根据多条件删除
     * @param conditions
     */
    void removeAll(Collection<Condition> conditions);

}
