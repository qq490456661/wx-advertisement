package com.onway.web.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/6 0006.
 */
@Controller
public class IndexController {


    @ResponseBody
    @RequestMapping("/index.do")
    public Object index(){
        System.out.println("index.do");
        return null;
    }


}
