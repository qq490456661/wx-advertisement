package com.onway.web.module.session.impl;

import com.onway.web.module.exception.InvalidSessionException;
import com.onway.web.module.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 暂时没用,应该会调用一个memSession
 * Created by linjunjie on 2015/12/6 (linjunjie@onway.com).
 */
//@Component("session")
public class HashMapSession implements Session {
    private Map<String,Object> session = new HashMap<String, Object>();

    /**
     * 根据key获取值
     *
     * @param request
     * @param key
     */
    @Override
    public Object getAttribute(HttpServletRequest request, String key) throws InvalidSessionException {
        return null;
    }

    /**
     * 设置值
     *
     * @param request
     * @param response
     * @param key
     * @param obj
     */
    @Override
    public void setAttribute(HttpServletRequest request, HttpServletResponse response, String key, Serializable obj) throws InvalidSessionException {

    }

    /**
     * 判断是否含有值
     *
     * @param request
     * @param key
     */
    @Override
    public boolean hasAttribute(HttpServletRequest request, String key) throws InvalidSessionException {
        return false;
    }

    /**
     * 获取用户信息
     *
     * @param request
     */
    @Override
    public Object getUserInfoAttribute(HttpServletRequest request) throws InvalidSessionException {
        return null;
    }

    /**
     * 设置用户信息
     *
     * @param request
     * @param response
     * @param obj
     */
    @Override
    public void setUserInfoAttribute(HttpServletRequest request, HttpServletResponse response, Serializable obj) throws InvalidSessionException {

    }
}
