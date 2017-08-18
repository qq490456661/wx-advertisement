package com.onway.web.controller;

import com.google.gson.Gson;
import com.onway.web.controller.base.BaseAction;
import com.onway.web.dao.UserDao;
import com.onway.web.module.Config;
import com.onway.web.module.request.AddUserRequest;
import com.onway.web.module.request.SelectByIdRequest;
import com.onway.web.module.response.AddUserResponseList;
import com.onway.web.module.response.Response;
import com.onway.web.pojo.User;
import com.onway.web.pojo.UserPathPojo;
import com.onway.web.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/6 0006.
 */
@SpringBootApplication
@ComponentScan("com.onway.web.**")
@ServletComponentScan("com.onway.web.**")
@EnableAutoConfiguration
@EnableConfigurationProperties({Config.class})
@RestController
@EnableTransactionManagement
public class DemoApplication extends BaseAction{

    @Autowired
    private UserDao userDao;

    @Autowired
    private Config config;


    //@RequestMapping("/index.html")
    public String test(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(config));
        UserPojo userPojoList = userDao.get("zhangsan");
        System.out.println(gson.toJson(userPojoList));
        return "hello world!";
    }
    @RequestMapping("/index.html")
    public String index(){
        Gson gson = new Gson();
        User user = userDao.select(1);
        System.out.println(gson.toJson(user));
        return "hello world!";
    }
    @RequestMapping("/insert.html")
    public String insert(){
        int id=1;
        LocalDate dateNow =LocalDate.now();

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = dateNow.atStartOfDay().atZone(zone).toInstant();
        Date date=Date.from(instant);
        Gson gson = new Gson();
        userDao.insert("www.baidu.com","","www.baidu.com","www.baidu.com", date,"www.baidu.com","www.baidu.com","","","");
        //System.out.println(gson.toJson(userPath));
        return "hello world!";
    }
    @RequestMapping("/update")
    public void update(@Param("id")int id, @Param("userPath")String userPath,
                           @Param("userUrl")String userUrl, @Param("userTitle")String userTitle,
                           @Param("userAuthor")String userAuthor, @Param("userDate")String userDate,
                           @Param("userQrcode")String userQrcode, @Param("cell")String cell,
                           @Param("userFullAd")String userFullAd, @Param("userBottomAd")String userBottomAd,
                           @Param("userBottomText")String userBottomText){
        Date date=null;
        if(userDate.length()!=0){
            date=new Date(userDate);
        }
        userDao.update(id,userPath,userUrl,userTitle,userAuthor,date,userQrcode,cell,userFullAd,userBottomAd,userBottomText);
    }
    @RequestMapping("/selectById")
    public Response selectById(SelectByIdRequest request){
        Response response = new Response(request);
        UserPathPojo userPath =userDao.selectById(1);
        response.setData(userPath);
        return response;
    }
    @RequestMapping("/addUser")
    public Response getRediect(AddUserRequest request){

        Response response = new Response(request);

        List<UserPojo> userPojoList = new ArrayList<UserPojo>();
        UserPojo userPojo = new UserPojo();
        userPojo.setUserId("123");
        userPojo.setUserName("xxxx");
        userPojo.setGmtCreate(new Date());
        userPojoList.add(userPojo);
        UserPojo userPojoa = new UserPojo();
        userPojoa.setUserId("222");
        userPojoa.setUserName("gfggg");
        userPojoa.setGmtCreate(new Date());
        userPojoList.add(userPojoa);

       /* AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setUserName(userPojo.getUserName());
        addUserResponse.setUserId(userPojo.getUserId());
        addUserResponse.setGmtCreate("2017-05");*/
        AddUserResponseList view = new AddUserResponseList();
        view.toResponse(userPojoList);
        response.setData(view);

        return  response;
    }



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
