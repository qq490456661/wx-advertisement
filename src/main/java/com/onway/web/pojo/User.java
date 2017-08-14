package com.onway.web.pojo;

import java.util.Date;

/**
 * Created by win7 on 2017/8/14.
 */
public class User {
    private int id;
    private String userId;
    private String realName;
    private int userTimes;
    private String passwd;
    private String call;
    private String accoessToken;
    private String delayToken;
    private String email;
    private String poolCode;
    private Date useLasttime;
    private Date gmtCreate;
    private Date gmtStarttime;
    private Date gmtEndtime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getUserTimes() {
        return userTimes;
    }

    public void setUserTimes(int userTimes) {
        this.userTimes = userTimes;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getAccoessToken() {
        return accoessToken;
    }

    public void setAccoessToken(String accoessToken) {
        this.accoessToken = accoessToken;
    }

    public String getDelayToken() {
        return delayToken;
    }

    public void setDelayToken(String delayToken) {
        this.delayToken = delayToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public Date getUseLasttime() {
        return useLasttime;
    }

    public void setUseLasttime(Date useLasttime) {
        this.useLasttime = useLasttime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtStarttime() {
        return gmtStarttime;
    }

    public void setGmtStarttime(Date gmtStarttime) {
        this.gmtStarttime = gmtStarttime;
    }

    public Date getGmtEndtime() {
        return gmtEndtime;
    }

    public void setGmtEndtime(Date gmtEndtime) {
        this.gmtEndtime = gmtEndtime;
    }
}

