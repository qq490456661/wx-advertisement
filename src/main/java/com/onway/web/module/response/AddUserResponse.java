package com.onway.web.module.response;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public class AddUserResponse{

    private int id;

    private String userName;

    private String userId;

    private String gmtCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
