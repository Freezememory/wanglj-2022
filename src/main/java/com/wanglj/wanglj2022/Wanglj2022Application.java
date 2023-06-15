package com.wanglj.wanglj2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class Wanglj2022Application {

    public static void main(String[] args) {
        SpringApplication.run(Wanglj2022Application.class, args);
    }

}
