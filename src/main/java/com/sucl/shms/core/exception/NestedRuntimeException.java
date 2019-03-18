package com.sucl.shms.core.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sucl
 * @since 2019/3/18
 */
@Slf4j
public class NestedRuntimeException extends org.springframework.core.NestedRuntimeException{

    public NestedRuntimeException(String msg) {
        super(msg);
    }

    public NestedRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    protected void trace(){

    }
}
