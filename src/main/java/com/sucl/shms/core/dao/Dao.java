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

    List<T> getAll();

    List<T> getAll(Collection<Condition> conditions);

    Pager<T> getPager(Pager pager,Collection<Condition> conditions,Collection<Order> orders);

    T get(PK id);

    T getInitializeObject(PK id,String [] props);

    boolean exist(PK id);

    boolean exist(String property,String value);

    T save(T t);

    int save(List<T> ts);

    void remove(PK id);

    void remove(String property,Object value);

    void removeAll(Collection<Condition> conditions);

}
