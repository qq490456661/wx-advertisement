package com.onway.web.module.exception;

/**
 * 用于Service层抛出异常
 * Created by linjunjie on 2015/11/28 (linjunjie@onway.com).
 */
public class ServiceException extends BaseException {


    private static final long serialVersionUID = 6933003405265681273L;

    public ServiceException(String message, int code) {
        super(message, code);
    }

    public ServiceException(String message, int code, Throwable e) {
        super(message, code, e);
    }
}
