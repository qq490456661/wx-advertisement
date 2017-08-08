package com.onway.web.module.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/3/1.
 */
public class ResponseResultConstant {
    public static Map<Integer,String> resultString = new HashMap<Integer, String>();
    /**
     * 数据库错误
     */
    public static int CODE_SERVICE_DB_ERROR = 200;
    /**
     * sql执行错误
     */
    public static int CODE_SERVICE_DB_SQL_ERROR = 201;
    /**
     * 无效会话
     */
    public static int INVALID_SESSION = 202;
    /**
     * 参数错误
     */
    public static int CODE_SERVICE_PARAMS_ERROR = 203;
    /**
     * 未知运行错误
     */
    public static int CODE_SERVICE_RUNTIME_ERROR = 204;
    /**
     * 密码错误
     */
    public static int USER_PASSWORD_ERROR = 205;

    /**
     * 用户不存在
     */
    public static int USER_NOT_EXISTS = 206;

    /**
     * 用户注册失败
     */
    public static int USER_REGISTER_ERROR = 207;

    /**
     * 请勿重复添加
     */
    public static int ALREADY_EXISTS_ERROR = 208;


    static{
        resultString.put(CODE_SERVICE_DB_ERROR,"数据库连接错误");
        resultString.put(CODE_SERVICE_DB_SQL_ERROR,"SQL运行错误,请联系客服人员");
        resultString.put(INVALID_SESSION,"会话失效,请重新登陆");
        resultString.put(CODE_SERVICE_PARAMS_ERROR,"传入参数错误");
        resultString.put(CODE_SERVICE_RUNTIME_ERROR,"未知的运行错误");
        resultString.put(USER_PASSWORD_ERROR,"用户名/密码错误");
        resultString.put(USER_NOT_EXISTS,"用户不存在");
        resultString.put(USER_REGISTER_ERROR,"创建用户失败");
        resultString.put(ALREADY_EXISTS_ERROR,"请勿重复添加");

    }

}
