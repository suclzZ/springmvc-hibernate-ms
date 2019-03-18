package com.sucl.shms.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/18
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

    public static List<Method> annotationedGetterMethod(Class targetClass, Class annotationClass) {
        List<Method> methods = new ArrayList<Method>();
        doWithMethods(targetClass, new MethodCallback() {
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                Annotation annotation = method.getAnnotation(annotationClass);
                if ((annotation != null) && (method.getName().startsWith("get")))
                    methods.add(method);
            }
        });
        return methods;
    }

    public static List<Field> annotationedField(Class targetClass, Class annotationClass) {
        List<Field> fields = new ArrayList<Field>();
        doWithFields(targetClass, new FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                Annotation annotation = field.getAnnotation(annotationClass);
                if ((annotation != null) && (!fields.contains(fields)))
                    fields.add(field);
            }
        });
        return fields;
    }

    public static Object getPrivateFieldValue(Object bean, String fieldName) {
        try {
            Field field = bean.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(bean);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
