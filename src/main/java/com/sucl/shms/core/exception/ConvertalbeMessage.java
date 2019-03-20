package com.sucl.shms.core.exception;

import com.sucl.shms.core.message.Message;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author sucl
 * @since 2019/3/20
 */
public interface ConvertalbeMessage {

    Message getMessage(MessageSource messageSource, Locale locale);
}
