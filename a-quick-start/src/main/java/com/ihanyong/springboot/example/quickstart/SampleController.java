package com.ihanyong.springboot.example.quickstart;/**
 * Created by hanyong on 2017/6/5.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author hanyong
 * @Date 2017/6/5
 */
@RestController
@SpringBootApplication
public class SampleController extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
    @RequestMapping("/")
    public String index() {
        LOGGER.info("receive request at {}", new Date());
        return "hello world";
    }


    /**
     * spring boot 的启动方法
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

//    public static void main(String[] args) {
//        new SpringApplication(SampleController.class)
////                .addListeners(new )
//                .run(args);
//    }


    /**
     * 发布到外部的容器(如:tomcat), 一定要重写SpringBootServletInitializer类的configure方法, 将servlet等bean bind 到 servlet容器里
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SampleController.class);
    }
}
