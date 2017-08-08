package com.onway.web.module.exception;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/3/1.
 */
public class BaseException extends Exception {
    private static final long serialVersionUID = 674319615780304662L;
    private int code; //错误码
    private String message; //消息
    private String front_message; //返回给前端的消息
    public BaseException(String message,int code){
        super();
        this.code = code;
        this.message = message;
    }

    public BaseException(String message,int code,String front_message){
        super();
        this.code = code;
        this.message = message;
        this.front_message = front_message;
    }

    public BaseException(String message,int code,Throwable e){
        super(message,e);
        this.message = message;
        this.code = code;
    }

    public BaseException(String message,int code,Throwable e,String front_message){
        super(message,e);
        this.message = message;
        this.code = code;
        this.front_message = front_message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFront_message() {
        return front_message;
    }

    public void setFront_message(String front_message) {
        this.front_message = front_message;
    }
}
