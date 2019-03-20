package com.sucl.shms.core.message;

import com.sucl.shms.core.exception.ConvertalbeMessage;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

/**
 * @author sucl
 * @since 2019/3/20
 */
@Data
public class Message implements ConvertalbeMessage {
    public static final String DEFAULT_ERR_CODE = "0000";

    private String code;
    private String msg;
    private String i18n;
    private String[] args;

    public Message(String msg){
        this(DEFAULT_ERR_CODE,msg);
    }

    public Message(String code,String  msg){
        this.code = code;
        this.msg = msg;
    }

    public Message(String i18n,String[] args){
        this.i18n = i18n;
        this.args = args;
        this.code = DEFAULT_ERR_CODE;
    }

    @Override
    public Message getMessage(MessageSource messageSource, Locale locale) {
        String msg = getMsg();
        if (StringUtils.isNotEmpty(this.i18n)) {
            try {
                msg = messageSource.getMessage(this.i18n, this.args, locale);
            } catch (NoSuchMessageException e) {
                msg = this.i18n;
            }
        }
        return new Message(this.code, msg);
    }
}
