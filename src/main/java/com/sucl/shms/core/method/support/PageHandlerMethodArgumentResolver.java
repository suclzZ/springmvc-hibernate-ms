package com.sucl.shms.core.method.support;

import com.sucl.shms.core.orm.Pager;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(com.sucl.shms.core.orm.annotation.Page.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        int pageIndex = getPageIndexFromRequest(webRequest);
        int pageSize = getPageSizeFromRequest(webRequest);
        return new Pager(pageIndex,pageSize,Pager.QUERY_TYPE_ALL);
    }

    private int getPageSizeFromRequest(WebRequest webRequest) {
        String pageSize = webRequest.getParameter("page:size");
        return new Integer(pageSize).intValue();
    }

    private int getPageIndexFromRequest(WebRequest webRequest) {
        String pageIndex = webRequest.getParameter("page:index");
        return new Integer(pageIndex).intValue();
    }
}
