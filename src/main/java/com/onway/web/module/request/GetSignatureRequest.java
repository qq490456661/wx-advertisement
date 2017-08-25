package com.onway.web.module.request;

/**
 * Created by win7 on 2017/8/25.
 */
public class GetSignatureRequest extends Request{
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
