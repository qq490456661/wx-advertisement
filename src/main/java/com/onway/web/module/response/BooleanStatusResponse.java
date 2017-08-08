package com.onway.web.module.response;

/**
 * Created by linjunjie on 2015/11/28 (linjunjie@onway.com).
 */
public class BooleanStatusResponse {

    Boolean status = false;

    public BooleanStatusResponse(Boolean status){
        this.status = status;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
