package com.onway.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * Created by zs on 2017/8/14 0014.
 */
public class ServletInitializer  extends SpringBootServletInitializer  {

        private static final Logger log = LoggerFactory.getLogger(ServletInitializer .class);

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(DemoApplication.class);
        }

}
