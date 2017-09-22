package com.onway.web.module.request;

import com.onway.web.module.request.Request;
import com.onway.web.module.response.Response;

public class PayRequest extends Request {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String prepay_id;
  public PayRequest(){

  }
  public PayRequest(String appId, String timeStamp, String nonceStr, String prepay_id,
      String paySign) {
    this.appId = appId;
    this.timeStamp = timeStamp;
    this.nonceStr = nonceStr;
    this.prepay_id = prepay_id;
    this.paySign = paySign;
  }

  public String getAppId() {

    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getPrepay_id() {
    return prepay_id;
  }

  public void setPrepay_id(String prepay_id) {
    this.prepay_id = prepay_id;
  }

  public String getPaySign() {
    return paySign;
  }

  public void setPaySign(String paySign) {
    this.paySign = paySign;
  }

  private String paySign;
}
