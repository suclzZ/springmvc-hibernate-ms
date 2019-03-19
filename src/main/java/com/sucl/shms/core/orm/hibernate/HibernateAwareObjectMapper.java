package com.sucl.shms.core.orm.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 解决@ResponseBody导致FetchType.LAZY失效问题
 * @author sucl
 * @since 2019/3/19
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper(){
        registerModule(new Hibernate4Module());
    }
}
