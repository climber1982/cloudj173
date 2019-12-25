package com.main;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication //springboot注解
@EnableEurekaServer //注册服务器
public class Register {
    public static void main(String[] args) {
        SpringApplication.run(Register.class,args);
    }
}
