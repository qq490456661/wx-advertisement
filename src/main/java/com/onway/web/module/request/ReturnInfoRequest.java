package com.onway.web.module.request;

/**
 * Created by win7 on 2017/8/24.
 */
public class ReturnInfoRequest extends Request{
    private String timestamp;
    private String signature;
    private String nonceStr;

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

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
