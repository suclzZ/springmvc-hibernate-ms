package com.sucl.shms.core.web;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author sucl
 * @since 2019/3/16
 */
public class BaseController {

    @ExceptionHandler
    public void handlerException(Exception exception){

    }
}
