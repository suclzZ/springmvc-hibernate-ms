package com.sucl.shms.core.orm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author sucl
 * @since 2019/3/16
 */
//处理序列号异常
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public interface Domain extends Serializable {
}
