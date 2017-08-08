package com.onway.web.module.exception;

/**
 * Created by linjunjie on 2015/12/28 (linjunjie@onway.com).
 */
public class DBConnectionException extends Exception {

    private String serviceName;

    private String method;

    public DBConnectionException(String message,Throwable e){
        super(message,e);
    }

    public DBConnectionException(String serviceName,String method){
        this.serviceName = serviceName;
        this.method = method;
    }

    public DBConnectionException setServiceName(String serviceName){
        this.serviceName = serviceName ;
        return this;
    }

    public DBConnectionException setMethod(String method){
        this.method = method;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getMethod() {
        return method;
    }
}
