package com.onway.web.controller;


import com.onway.web.dao.UserDao;
import com.onway.web.module.request.ReturnInfoRequest;
import com.onway.web.module.response.Response;
import com.onway.web.pojo.UserPathPojo;
import com.onway.web.pojo.UserPojo;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by win7 on 2017/8/21.
 */
@Controller
public class GetOpenIdController {
    private static final String AppId="wxa16425d3780729a5";
    private static final String AppSecret="0c6e4d07003bbe0afa850656980979bd";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;
    @RequestMapping("/getAccessToken.do")
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
    public void getOpenId(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        String code=httpServletRequest.getParameter("code");

        HttpSession session = httpServletRequest.getSession();
        String openId= (String) session.getAttribute("openId");
        if(openId==null){
            try {
                DefaultHttpClient client = new DefaultHttpClient();
                    String getOpenId="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid="+AppId+"&secret="+AppSecret+"&code="+code+"&grant_type=authorization_code";
                    HttpGet request = new HttpGet(getOpenId);
                    HttpResponse response = client.execute(request);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        String strResult = EntityUtils.toString(response.getEntity());
                        System.out.println("get请求结果:" + strResult);
                        log.info("get请求结果:" + strResult);
                        JSONObject jsonResult = new JSONObject(strResult);
                        openId = (String) jsonResult.get("openid");
                        System.out.println("get请求结果openid:" + openId);
                        //获取AccessToken
                        String access_token= (String) session.getAttribute("access_token");
                        if(access_token==null|| access_token.length()==0){
                            access_token=getAccessToken();
                            session.setAttribute("access_token",access_token);
                        }
                        String getUser="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
                        request = new HttpGet(getUser);
                        response = client.execute(request);
                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                            strResult = EntityUtils.toString(response.getEntity());
                            jsonResult = new JSONObject(strResult);
                            String nickname= (String) jsonResult.get("nickname");
                            int sex= (int) jsonResult.get("sex");
                            String city= (String) jsonResult.get("city");
                            String country= (String) jsonResult.get("country");
                            String province= (String) jsonResult.get("province");
                            String headimgurl= (String) jsonResult.get("headimgurl");
                            UserPojo userPojo=userDao.selectUserById(openId);
                            if(userPojo==null){
                                userDao.insertUser(openId,nickname,sex,city,country,province,headimgurl,"","","",null,null);
                            }
                            UserPathPojo userPathPojo=userDao.selectById(openId);
                            if(userPathPojo==null){
                                System.out.println(openId);
                                userDao.insert(openId,"","","","",null,"","","","","");
                            }
                        }
                    }
            } catch (IOException e) {
                //System.out.println("get请求提交失败:" + access_token_url + e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        UserPojo userPojo=userDao.selectUserById(openId);
        try {
            httpServletResponse.sendRedirect("/link.html?openId="+userPojo.getOpenId());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/getSignature.do")
    @ResponseBody
    public Response getSignature(HttpServletRequest httpServletRequest, ReturnInfoRequest returnInfoRequest){
        Response response=new Response(returnInfoRequest);
        HttpSession session = httpServletRequest.getSession();
        String timestamp= (String) session.getAttribute("timestamp");
        String signature= (String) session.getAttribute("signature");
        String nonceStr= (String) session.getAttribute("nonceStr");
        String msg_desc= (String) session.getAttribute("msg_desc");
        String msg_title= (String) session.getAttribute("msg_title");
        String msg_cdn_url= (String) session.getAttribute("msg_cdn_url");
        returnInfoRequest.setTimestamp(timestamp);
        returnInfoRequest.setSignature(signature);
        returnInfoRequest.setNonceStr(nonceStr);
        returnInfoRequest.setNonceStr(msg_desc);
        returnInfoRequest.setNonceStr(msg_title);
        returnInfoRequest.setNonceStr(msg_cdn_url);
        response.setData(returnInfoRequest);
        return response;
    }
}
