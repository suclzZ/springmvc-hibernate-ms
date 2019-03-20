package com.sucl.shms.core.exception;

import com.sucl.shms.core.message.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *  返回json作为异常处理结果
 * @author sucl
 * @since 2019/3/19
 */
@ControllerAdvice
public class ControllerAdviceHandler {

    public ControllerAdviceHandler(){
        System.out.println("const");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message exception(Exception ex){
        ex.printStackTrace();
        Message message = new Message(ex.getMessage());
        return message;
    }

}
