package com.onway.web.module.exception;

import com.onway.web.module.exception.BaseException;

/**
 * 用于Session读/取 抛出异常
 * Created by linjunjie on 2015/11/28 (linjunjie@onway.com).
 */
public class InvalidSessionException extends BaseException {


    private static final long serialVersionUID = 4391621326243662757L;

    public InvalidSessionException(String message, int code) {
        super(message, code);
    }

    public InvalidSessionException(String message, int code, Throwable e) {
        super(message, code, e);
    }
}
