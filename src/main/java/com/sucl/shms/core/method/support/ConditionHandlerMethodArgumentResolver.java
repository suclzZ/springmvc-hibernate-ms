package com.sucl.shms.core.method.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Domain;
import com.sucl.shms.core.orm.annotation.QueryCondition;
import com.sucl.shms.core.orm.hibernate.HibernateCondition;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 查询条件参数转换
 * 结合mybatis-plus整合
 */
public class ConditionHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String CONDITIONS = "conditions";
    private Map<String, Set<String>> cachedDomainFieldNames = Collections.synchronizedMap(new HashMap());

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CollectionUtils.class.isAssignableFrom(parameter.getParameterType())
                && parameter.hasParameterAnnotation(QueryCondition.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        QueryCondition queryCondition = parameter.getParameterAnnotation(QueryCondition.class);
        Class<?> domainClazz = queryCondition.domain();
        Set<String> fields = getDomainParamNames(domainClazz);
        String conditionStr = webRequest.getParameter(CONDITIONS);
        Collection<Condition> conditions;
        if(StringUtils.isNotEmpty(conditionStr)){
            ObjectMapper objectMapper = new ObjectMapper();
            conditions = objectMapper.readValue(conditionStr,new TypeReference<List<Condition>>(){});
        }else{
            Map<String, String> parameters = requestParameterToMap(webRequest);
            conditions = mapToConditions(parameters);
        }
        validateCondition(conditions,fields);
        return conditions;
    }

    private void validateCondition(Collection<Condition> conditions, Set<String> fields) {
        Iterator<Condition> it = conditions.iterator();
        while (it.hasNext()){
            if(!fields.contains(it.next())){
                it.remove();
            }
        }
    }

    private Collection<Condition> mapToConditions(Map<String,String> parameters) {
        Collection<Condition> conditions = new ArrayList<Condition>();
        if(MapUtils.isNotEmpty(parameters)){
            for(Map.Entry<String,String> entry : parameters.entrySet()){
                ((ArrayList<Condition>) conditions).add(new HibernateCondition(entry.getKey(),entry.getValue()));
            }
        }
        return conditions;
    }

    private Map<String,String> requestParameterToMap(WebRequest webRequest) {
        Map<String,String[]> parameterMap = webRequest.getParameterMap();
        Map<String,String> parameters = new HashMap<>();
        if(parameterMap!=null){
            for(Map.Entry<String,String[]> entry : parameterMap.entrySet()){
                String[] vs = entry.getValue();
                String value = vs!=null&&vs.length>0?vs[0]:null;
                parameters.put(entry.getKey(),value);
            }
        }
        return parameters;
    }

    private Set<String> getDomainParamNames(Class<?> domainClazz){
        String domainClazzName = domainClazz.getName();
        Set<String> paramNames = new HashSet<String>();
        if (cachedDomainFieldNames.containsKey(domainClazzName)) {
            paramNames = cachedDomainFieldNames.get(domainClazzName);
        }else {
            ConditionFieldCallback conditionFieldCallback = new ConditionFieldCallback("");
            ReflectionUtils.doWithFields(domainClazz, conditionFieldCallback);
            paramNames.addAll(conditionFieldCallback.getFieldNames());
            this.cachedDomainFieldNames.put(domainClazzName, paramNames);
        }
        return paramNames;
    }

    private class ConditionFieldCallback implements ReflectionUtils.FieldCallback{
        private List<String> fieldNames = new ArrayList();
        private String prefix = "";

        public ConditionFieldCallback(String prefix) {
            this.prefix = Objects.toString(prefix,"");
        }

        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException{
            String fieldName = field.getName();
            Id tableId = field.getAnnotation(Id.class);
            if (tableId!=null) {
                this.fieldNames.add(this.prefix + fieldName);
            }else {
                Class fieldClass = field.getType();
                if (Domain.class.isAssignableFrom(fieldClass)){
                    if (this.prefix.split("\\.").length > 3) {
                        return;
                    }
                    ConditionFieldCallback conditionFieldCallback = new ConditionFieldCallback( this.prefix + field.getName() + ".");
                    ReflectionUtils.doWithFields(fieldClass, conditionFieldCallback);
                    this.fieldNames.addAll(conditionFieldCallback.getFieldNames());
                }
            }
        }
        public List<String> getFieldNames() {
            return this.fieldNames;
        }

    }
}
