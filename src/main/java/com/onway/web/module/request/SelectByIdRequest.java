package com.onway.web.module.request;

import java.util.Date;

/**
 * Created by win7 on 2017/8/17.
 */
public class SelectByIdRequest extends Request{

    private String openId;
    private String timestamp;
    private String signature;
    private String noncestr;
    private String msgDesc;
    private String msgTitle;
    private String msgCdnUrl;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

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
}
