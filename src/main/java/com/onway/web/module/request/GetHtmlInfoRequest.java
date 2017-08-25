package com.onway.web.module.request;

/**
 * Created by win7 on 2017/8/25.
 */
public class GetHtmlInfoRequest extends Request{
    private String msgDesc;
    private String msgTitle;
    private String msgCdnUrl;

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
