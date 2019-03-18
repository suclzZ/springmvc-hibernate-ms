package com.sucl.shms.core.exception;

/**
 * @author sucl
 * @since 2019/3/18
 */
public class BusException extends NestedRuntimeException{

    public BusException(String msg) {
        super(msg);
    }

    public BusException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
