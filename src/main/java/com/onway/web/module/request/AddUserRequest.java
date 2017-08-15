package com.onway.web.module.request;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public class AddUserRequest extends Request {


    private String userId;

    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
