package com.lovo.cloud.controller;

import com.lovo.cloud.entity.UserEntity;
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
    public UserEntity getUserByTag(@PathVariable("tag")int tag){

        ResponseEntity responseEntity=     restTemplate.getForEntity("http://cloudc/userInfo/"+tag,UserEntity.class);

        return (UserEntity) responseEntity.getBody();
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
