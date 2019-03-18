package com.sucl.shms.core.orm.annotation;

import com.sucl.shms.core.orm.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sucl
 * @since 2019/3/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Sort
{
    boolean value() default true;

    Order.Direction direct() default Order.Direction.DESC;
}
