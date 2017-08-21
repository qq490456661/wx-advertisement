package com.onway.web.controller;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by win7 on 2017/8/21.
 */
@Controller
public class GetOpenIdController {
    private static final String AppId="wxa16425d3780729a5";
    private static final String AppSecret="0c6e4d07003bbe0afa850656980979bd";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/getAccessToken.do")
    @ResponseBody
    public String  getAccessToken(){
        String access_token = "";
        String aturl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppId
                + "&secret=" + AppSecret;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(aturl);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                // System.out.println("get请求结果:" + strResult);
                JSONObject jsonResult = new JSONObject(strResult);
                System.out.println(jsonResult);
                access_token = (String) jsonResult.get("access_token");
                System.out.println("get请求结果   access_token  :   " + access_token);
            }
        } catch (IOException e) {
                 //System.out.println("get请求提交失败:" + access_token_url + e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return access_token;
    }
    @RequestMapping("/getOpenId.do")

    public String getOpenId(){
        String code = "";
        String openid="";
        String aturl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+AppId+"&redirect_uri=http://weixin.puyuekeji.com/link.html&response_type=code&scope=snsapi_userinfo#wechat_redirect";

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(aturl);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println("get请求结果:" + strResult);
                JSONObject jsonResult = new JSONObject(strResult);
                System.out.println(jsonResult);
                code = (String) jsonResult.get("code");
                System.out.println("get请求结果Code:"+code);
                log.info("get请求结果Code:"+code);
                String getOpenId="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+AppId+"&secret="+AppSecret+"&code="+code+"&grant_type=authorization_code";
                request = new HttpGet(getOpenId);
                response = client.execute(request);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    strResult = EntityUtils.toString(response.getEntity());
                    System.out.println("get请求结果:" + strResult);
                    log.info("get请求结果:" + strResult);
                    jsonResult = new JSONObject(strResult);
                    openid = (String) jsonResult.get("openid");
                    System.out.println("get请求结果openid:" + openid);
                    log.info("get请求结果openid:" + openid);
                }
            }
        } catch (IOException e) {
            //System.out.println("get请求提交失败:" + access_token_url + e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "link.html";
    }
}
