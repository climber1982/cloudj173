package com.lovo.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    //手动装载远程调用模板
    @Bean(value = "restTemplate")
    @LoadBalanced  //负载均衡
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
