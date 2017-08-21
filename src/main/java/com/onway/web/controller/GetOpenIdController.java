package com.onway.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by win7 on 2017/8/21.
 */
@Controller
public class GetOpenIdController {
    @RequestMapping("/getOpenId.do")
    public void getOpenId(HttpServletRequest request, HttpServletResponse response){
        
    }
}
