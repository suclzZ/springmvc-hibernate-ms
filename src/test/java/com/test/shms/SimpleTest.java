package com.test.shms;

import com.sucl.shms.core.util.ReflectionUtils;
import com.sucl.shms.system.entity.User;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/18
 */
public class SimpleTest {

    public static void main(String[] args) {

        System.out.println(Math.ceil(new Double(19).doubleValue() / 10));

        List<Method> methods = ReflectionUtils.annotationedGetterMethod(User.class, Column.class);

        List<Field> fields = ReflectionUtils.annotationedField(User.class, Column.class);

        System.out.println(fields);

    }
}
