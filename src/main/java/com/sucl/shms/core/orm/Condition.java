package com.sucl.shms.core.orm;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询
 * @author sucl
 * @since 2019/3/18
 */
public interface Condition {

    String getProperty();

    Operate getOperate();

    Object getValue();

    enum Operate{
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        IN,
        LIKE,
        BTW, LLIKE, RLIKE, NOT_IN, IS_NULL, NOT_NULL;
    }
}
