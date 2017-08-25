package com.onway.web.pojo;

import java.util.Date;

/**
 * Created by win7 on 2017/8/14.
 */
public class UserPathPojo {
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

    private int id;
    private String openId;
    private String userPath;
    private String userUrl;
    private String userTitle;
    private String userAuthor;
    private Date userDate;
    private String userQrcode;
    private String userFullAd;
    private String userBottomAd;
    private String userBottomText;
    private String cell;
    private String timestamp;
    private String signature;
    private String noncestr;
    private String msgDesc;
    private String msgTitle;
    private String msgCdnUrl;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgCdnUrl() {
        return msgCdnUrl;
    }

    public void setMsgCdnUrl(String msgCdnUrl) {
        this.msgCdnUrl = msgCdnUrl;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(String userAuthor) {
        this.userAuthor = userAuthor;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public String getUserQrcode() {
        return userQrcode;
    }

    public void setUserQrcode(String userQrcode) {
        this.userQrcode = userQrcode;
    }

    public String getUserFullAd() {
        return userFullAd;
    }

    public void setUserFullAd(String userFullAd) {
        this.userFullAd = userFullAd;
    }

    public String getUserBottomAd() {
        return userBottomAd;
    }

    public void setUserBottomAd(String userBottomAd) {
        this.userBottomAd = userBottomAd;
    }

    public String getUserBottomText() {
        return userBottomText;
    }

    public void setUserBottomText(String userBottomText) {
        this.userBottomText = userBottomText;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
