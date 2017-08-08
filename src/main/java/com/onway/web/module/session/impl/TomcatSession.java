package com.onway.web.module.session.impl;

import com.onway.web.module.exception.InvalidSessionException;
import com.onway.web.module.session.Session;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Tomcat默认的Session管理
 * Created by linjunjie on 2015/12/6 (linjunjie@onway.com).
 */
@Component("session")
public class TomcatSession implements Session {



    /**
     * 根据key获取值
     *
     * @param request
     * @param key
     */
    @Override
    public Object getAttribute(HttpServletRequest request, String key) throws InvalidSessionException {
        return request.getSession().getAttribute(key);
    }

    /**
     * 设置值
     * obj : 需要实现序列化接口
     * @param request
     * @param response
     * @param key
     * @param obj
     */
    @Override
    public void setAttribute(HttpServletRequest request, HttpServletResponse response, String key, Serializable obj) throws InvalidSessionException {
        request.getSession().setAttribute(key,obj);
    }

    /**
     * 判断是否含有值
     *
     * @param request
     * @param key
     */
    @Override
    public boolean hasAttribute(HttpServletRequest request, String key) throws InvalidSessionException {
        if(request.getSession().getAttribute(key) != null){
            return true;
        }
        return false;
    }

    /**
     * 获取用户信息
     *
     * @param request
     */
    @Override
    public Object getUserInfoAttribute(HttpServletRequest request) throws InvalidSessionException {
        //return request.getSession().getAttribute(UserConstant.USER_INFO_KEY);
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
        //request.getSession().setAttribute(UserConstant.USER_INFO_KEY,obj);
    }
}
