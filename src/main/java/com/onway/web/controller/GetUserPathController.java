package com.onway.web.controller;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by win7 on 2017/8/22.
 */
@Controller
public class GetUserPathController {
    @RequestMapping("/getUserPath.do")
    public void getUserPath(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "openId") String openId){
        try {
            response.sendRedirect("info.html?openId="+openId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
