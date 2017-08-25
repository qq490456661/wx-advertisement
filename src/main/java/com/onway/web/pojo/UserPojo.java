package com.onway.web.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/6 0006.
 */
public class UserPojo {
    private int id;
    private String openId;
    private String nickName;
    private int sex;
    private String city;
    private String country;
    private String province;
    private String headimgUrl;
    private String userLevel;
    private String status;
    private String accoessToken;
    private Date gmtStartTime;
    private Date gmtEndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHeadimgUrl() {
        return headimgUrl;
    }

    public void setHeadimgUrl(String headimgUrl) {
        this.headimgUrl = headimgUrl;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccoessToken() {
        return accoessToken;
    }

    public void setAccoessToken(String accoessToken) {
        this.accoessToken = accoessToken;
    }

    public Date getGmtStartTime() {
        return gmtStartTime;
    }

    public void setGmtStartTime(Date gmtStartTime) {
        this.gmtStartTime = gmtStartTime;
    }

    public Date getGmtEndTime() {
        return gmtEndTime;
    }

    public void setGmtEndTime(Date gmtEndTime) {
        this.gmtEndTime = gmtEndTime;
    }
}
