package com.onway.web.module.session;


import com.onway.web.module.exception.InvalidSessionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Session
 * Created by linjunjie on 2015/12/7 (linjunjie@onway.com).
 */
public interface Session {

    /**
     * 根据key获取值
     */
    public Object getAttribute(HttpServletRequest request, String key) throws InvalidSessionException;

    /**
     * 设置值
     */
    public void setAttribute(HttpServletRequest request, HttpServletResponse response, String key, Serializable obj) throws InvalidSessionException;

    /**
     * 判断是否含有值
     */
    public boolean hasAttribute(HttpServletRequest request, String key) throws InvalidSessionException;

    /**
     * 获取用户信息
     */
    public Object getUserInfoAttribute(HttpServletRequest request) throws InvalidSessionException;

    /**
     * 设置用户信息
     */
    public void setUserInfoAttribute(HttpServletRequest request, HttpServletResponse response, Serializable obj) throws InvalidSessionException;

}
