package com.onway.web.module.request;

import java.io.Serializable;

/**
 * Created by linjunjie on 2015/11/28 (linjunjie@onway.com).
 */
public class Request implements Serializable {

    private String api_name = "";//

    private int pageNo;

    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }
}
