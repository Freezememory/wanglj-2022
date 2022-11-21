package com.wanglj.wanglj2022.controller;

import com.wanglj.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wanglj
 * @Date: 2022/11/21 16:19
 * @Description :
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("sayHello")
    private String getHello(@RequestParam(value = "userName")String userName){
           return helloService.sayHello(userName);
    }

    @GetMapping("/get")
    private String get(){
        return "wanglj ni hao ";
    }
}
