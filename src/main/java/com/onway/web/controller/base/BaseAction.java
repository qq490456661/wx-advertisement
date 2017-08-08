package com.onway.web.controller.base;

import com.onway.web.handle.ExceptionHandle;
import com.onway.web.module.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 异常拦截集中处理;
 * 将request和response存储在ThreadLocal中
 * 创建session;
 * Created by junjie.lin on 2017/08/08.
 */
public class BaseAction implements Serializable {

    private static final long serialVersionUID = 2504336653791589091L;

    //页码
    private static final String PAGE_NO = "pageNo";
    //每页条数
    private static final String PAGE_SIZE = "pageSize";
    //初始页码
    private static final int PAGE_NO_DIGIT = 1;
    //初始条数
    private static final int PAGE_SIZE_DIGIT = 10;

    private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>();
    private static final ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<HttpServletResponse>();

    public int adjustPageNo(HttpServletRequest request){
        String pageNo = request.getParameter(PAGE_NO);
        if(pageNo == null){
            return PAGE_NO_DIGIT;
        }
        return Integer.parseInt(pageNo);
    }

    public int adjustPageSize(HttpServletRequest request){
        String pageSize = request.getParameter(PAGE_SIZE);
        if(pageSize == null){
            return PAGE_SIZE_DIGIT;
        }
        return Integer.parseInt(pageSize);
    }

     //* 在任何action中的方法执行前，执行这个方法
    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response){
        requestThreadLocal.set(request);
        responseThreadLocal.set(response);
    }

    public HttpServletRequest getRequest(){
        return requestThreadLocal.get();
    }

    public HttpServletResponse getResponse(){
        return responseThreadLocal.get();
    }

    //* 集中处理action抛出的异常
    @ExceptionHandler({Exception.class})
    public void exceptionHandle(HttpServletRequest req,HttpServletResponse resp,Exception e){
        ExceptionHandle.handle(req, resp, e);
    }

    //集中式session,分为memSession和tomcatSession

    //@Qualifier("oscSession")
    @Resource
    private Session session;

    /**
     *  从session中取出存在里面的用户对象
     * @return
     * @throws com.onway.module.exception.InvalidSessionException
     */
    /*public User getUser()throws InvalidSessionException {
        User user = (User)session.getAttribute(getRequest(), UserConstant.USER_INFO_KEY);
        if(user == null){
            throw new InvalidSessionException("会话失效invalid-session", ResponseResultConstant.INVALID_SESSION);
        }
        return user;
    }*/


}
