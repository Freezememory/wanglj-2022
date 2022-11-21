package com.wanglj.wanglj2022.config;

import com.wanglj.hello.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wanglj
 * @Date: 2022/11/21 18:07
 * @Description :
 */
@Configuration
@Slf4j
public class HelloServiceConfig {

    @Bean
    public HelloService helloService(){

        log.info("加载自定义helloService!!!!");
        HelloService helloService = new HelloService();


        return helloService;

    }
}
