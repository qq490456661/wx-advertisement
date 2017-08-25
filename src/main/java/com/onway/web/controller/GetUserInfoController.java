package com.onway.web.controller;

import com.onway.web.dao.UserDao;
import com.onway.web.module.request.SelectByIdRequest;
import com.onway.web.module.response.Response;
import com.onway.web.pojo.UserPathPojo;
import com.onway.web.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by win7 on 2017/8/22.
 */
@RestController
public class GetUserInfoController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/getUserInfo.do")
    public Map getUserInfo(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "openId")String openId){
        UserPojo userPojo=userDao.selectUserById(openId);
        Map map=new HashMap();
        if(userPojo!=null){
            map.put("nickname",userPojo.getNickName());
            map.put("headimgUrl",userPojo.getHeadimgUrl());
        }
        return map;
    }
    @RequestMapping("/selectById.do")
    public Response selectById(SelectByIdRequest selectByIdRequest,HttpServletRequest request){
        Response response=new Response(selectByIdRequest);
        String openId="";
        openId=request.getParameter("openId");
        if(openId.length()==0){
            HttpSession session=request.getSession();
            openId= (String) session.getAttribute("openId");
        }
        UserPathPojo userPathPojo=userDao.selectById(openId);
        selectByIdRequest.setOpenId(userPathPojo.getOpenId());
        selectByIdRequest.setTimestamp(userPathPojo.getTimestamp());
        selectByIdRequest.setNoncestr(userPathPojo.getNoncestr());
        selectByIdRequest.setSignature(userPathPojo.getSignature());
        selectByIdRequest.setMsgTitle(userPathPojo.getMsgTitle());
        selectByIdRequest.setMsgCdnUrl(userPathPojo.getMsgCdnUrl());
        selectByIdRequest.setMsgDesc(userPathPojo.getMsgDesc());
        response.setData(selectByIdRequest);
        return response;
    }
}
