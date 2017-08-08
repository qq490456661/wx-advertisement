package com.onway.web.filter;

import org.apache.log4j.MDC;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/28.
 */
@WebFilter(urlPatterns = "/*", filterName = "userCheckFilter")
public class UserCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;


        StringBuilder queryString = new StringBuilder(request.getMethod()).append(":").append(request.getRequestURI());
        String queryStringTo = "";
        if("POST".equals(request.getMethod())){
            if(request.getContentType() != null && request.getContentType().contains("application/x-www-form-urlencoded")){
                queryStringTo = queryString.toString();
                Map<String,String[]> map = request.getParameterMap();
                if(!CollectionUtils.isEmpty(map)){
                    Set<String> set = map.keySet();
                    Iterator<String> iterator = set.iterator();
                    String key = null;
                    queryString.append("?");
                    String[] values = null;
                    int i=0;
                    while(iterator.hasNext()){
                        key = iterator.next();
                        values = map.get(key);
                        for(i=0;i<values.length;i++) {
                            if(i == values.length - 1){
                                queryString.append(key).append("=").append(values[i]).append("&");
                            }else {
                                queryString.append(key).append("=").append(values[i]).append("&");
                            }
                        }

                    }
                    queryStringTo = queryString.toString();
                    queryStringTo = queryStringTo.substring(0,queryStringTo.length() - 1);
                }

            }else {
                //无法获取POST参数
                queryString.append(" 【don't get args】");
                queryStringTo = queryString.toString();
            }
        }else {
            queryString.append("?").append(request.getQueryString());
            queryStringTo = queryString.toString();
        }

        MDC.put("ip", getIpAddress(request));
        //MDC.put("phone", request.getParameter("phone") == null ? "" : request.getParameter("phone"));
        MDC.put("uri", queryStringTo);
        MDC.put("timekey", UUID.randomUUID().toString().replaceAll("-", ""));

        //放行
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }

    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
