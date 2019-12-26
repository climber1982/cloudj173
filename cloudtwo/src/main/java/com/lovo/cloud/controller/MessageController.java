package com.lovo.cloud.controller;

import com.lovo.cloud.entity.UserEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageController {

    //注入远程调用模板
    @Autowired
    private RestTemplate restTemplate;

     @RequestMapping("getMessage")
    public String getMessage(){
         ResponseEntity responseEntity=     restTemplate.getForEntity("http://cloudc/printMessage",String.class);
        String message=responseEntity.getBody().toString();
         return message;
    }

    //根据标记获得用户对象1-赵云，2-马超
    @RequestMapping("userInfo/{tag}")
    @HystrixCommand(fallbackMethod = "getUserByTag2")
    public String getUserByTag(@PathVariable("tag")int tag){
              int i=1/0;
        ResponseEntity responseEntity=     restTemplate.getForEntity("http://cloudc/userInfo/"+tag,String.class);

        return  responseEntity.getBody().toString();
    }
    public String getUserByTag2(@PathVariable("tag")int tag){

        ResponseEntity responseEntity=     restTemplate.getForEntity("http://cloudc/userInfo/"+tag,String.class);

        return  responseEntity.getBody().toString();
    }
    @RequestMapping("getUser")
    public UserEntity getUser(){
         UserEntity user=new UserEntity();
         user.setUserName("张飞");
         user.setPassword("123456");
        ResponseEntity responseEntity=     restTemplate.postForEntity("http://cloudc/getUser",user,UserEntity.class);

        return (UserEntity) responseEntity.getBody();
    }
}
