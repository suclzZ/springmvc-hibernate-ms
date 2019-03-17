package com.sucl.shms.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
public interface Dao<T,PK extends Serializable> {

    List<T> getAll();

    T get(PK id);

    boolean exist(PK id);

    boolean exist(String property,String value);

    T save(T t);

    int save(List<T> ts);

    void remove(PK id);

    void remove(String property,Object value);

}
