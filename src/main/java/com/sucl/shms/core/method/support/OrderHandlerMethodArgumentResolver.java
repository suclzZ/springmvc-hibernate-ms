package com.sucl.shms.core.method.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.annotation.QueryOrder;
import com.sucl.shms.core.orm.hibernate.HibernateOrder;
import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.*;

public class OrderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Collection.class.isAssignableFrom(parameter.getParameterType()) &&
                parameter.hasParameterAnnotation(QueryOrder.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String sortStr = webRequest.getParameter("sort");
        //{"property":"employeeNo","direction":"DESC"}
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> sortList = null;
        if(sortStr != null){
            sortList = objectMapper.readValue(sortStr, new TypeReference<List<Map<String, String>>>() { });
        }
        Collection<Order> orders = new ArrayList();
        if(sortList!=null){
            for(Map smap : sortList){
                String property = Objects.toString(smap.get("property"));
                Order.Direction direct = smap.get("direction") == null || "desc".equals(smap.get("direction")) ? Order.Direction.DESC : Order.Direction.ASC;
                ((ArrayList<Order>) orders).add(new HibernateOrder(property,direct));
            }
        }
        return orders;
    }
}
