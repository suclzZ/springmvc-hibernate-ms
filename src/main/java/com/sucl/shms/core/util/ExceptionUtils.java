package com.sucl.shms.core.util;

import com.sucl.shms.core.exception.BusException;
import com.sucl.shms.core.exception.ConvertalbeMessage;
import com.sucl.shms.core.message.Message;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author sucl
 * @since 2019/3/20
 */
public class ExceptionUtils {

    public static Message getErrorMessage(Exception ex, MessageSource messageSource, Locale locale) {

        if ((ex instanceof ConvertalbeMessage)) {
            return ((ConvertalbeMessage) ex).getMessage(messageSource, locale);
        }

        Throwable cause = ex.getCause();
        while (cause != null) {
            if ((cause instanceof ConvertalbeMessage)) {
                return ((ConvertalbeMessage) cause).getMessage(messageSource, locale);
            }
            cause = cause.getCause();
        }

        return new Message( ex.getMessage());
    }
}