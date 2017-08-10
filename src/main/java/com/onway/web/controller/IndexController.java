package com.onway.web.controller;

import com.onway.web.controller.base.BaseAction;
import com.onway.web.module.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/6 0006.
 */
@RestController
public class IndexController extends BaseAction {


    @RequestMapping("/index.do")
    public Response index(){
        Response response = new Response();
        int a = 5;

        int b = 0;
        System.out.println(a/b);
        System.out.println("index.do");
        return response;
    }


}
