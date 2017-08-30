package com.onway.web.controller;

import com.onway.web.controller.base.BaseAction;
import com.onway.web.dao.UserDao;
import com.onway.web.module.request.SelectUserShareByIdRequest;
import com.onway.web.module.response.Response;
import com.onway.web.module.response.SelectUserShareByIdResponseList;
import com.onway.web.pojo.UserSharePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import  java.util.Date;
import java.util.List;

/**
 * Created by win7 on 2017/8/29.
 */
@RestController
public class ShareController extends BaseAction {
    @Autowired
    UserDao userDao;
    //查询用户分享的文章
    @RequestMapping("/selectUserShareByOpenId.do")
    public Response selectUserShareByOpenId(@Param(value = "openId") String openId, SelectUserShareByIdRequest request){
        Response response=new Response(request);
        //openId="oJpmfwUN1cFIdo0inxFwCaE6Uboo";
        List<UserSharePojo> listUserSharePojo=userDao.selectUserShareByOpenId(openId);
        SelectUserShareByIdResponseList view =new SelectUserShareByIdResponseList();
        view.toResponse(listUserSharePojo);
        response.setData(view);
        return response;
    }
    //添加用户分享的文章
    @RequestMapping("/insertUserShare.do")
    public void insertUserShare(@Param(value = "openId") String openId,@Param(value = "title") String title,
                                @Param(value = "url") String url){
        try {
            //openId="oJpmfwUN1cFIdo0inxFwCaE6Uboo";
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.now();
            ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
            Date date = Date.from(zdt.toInstant());
            userDao.insertUserShare(openId,title,date,url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除用户分享的文章
    @RequestMapping("/deleteUserShareById.do")
    public void deleteUserShareById(@Param(value = "id") String id){
        userDao.deleteUserShareById(Integer.parseInt(id));
    }
}
