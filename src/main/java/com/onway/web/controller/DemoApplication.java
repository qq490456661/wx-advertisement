package com.onway.web.controller;

import com.google.gson.Gson;
import com.onway.web.controller.base.BaseAction;
import com.onway.web.dao.UserDao;
import com.onway.web.module.Config;
import com.onway.web.pojo.UserPojo;
import org.json.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class DemoApplication extends BaseAction{

    @Autowired
    private UserDao userDao;

    @Autowired
    private Config config;



    @RequestMapping("/index.html")
    public String test(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(config));
        UserPojo userPojoList = userDao.get("zhangsan");
        System.out.println(gson.toJson(userPojoList));
        return "hello world!";
    }


    @RequestMapping("/rediect/{key}")
    public void getRediect(@PathVariable("key")String key,HttpServletResponse response){
        try {
            response.sendRedirect(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
