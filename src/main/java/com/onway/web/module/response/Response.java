package com.onway.web.module.response;


import com.onway.web.module.request.Request;

import java.io.Serializable;

/**
 * Created by linjunjie on 2015/11/28 (linjunjie@onway.com).
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 4293679040941469997L;
    //参照raycloud规范来写

    private String api_name = "";

    private String result = "100"; //100表示成功，其他表示失败

    private String message = "";//信息

    private Object data;//业务数据

    public Response(Request request){
        this.api_name = request.getApi_name();
    }

    public Response(){

    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
