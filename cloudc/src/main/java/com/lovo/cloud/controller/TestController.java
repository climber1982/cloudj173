package com.lovo.cloud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.cloud.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
   @RequestMapping("printMessage")
    public String printMessage(){
        return "hello cloud";
    }
  //根据标记获得用户对象1-赵云，2-马超
    @RequestMapping("userInfo/{tag}")
    public String  getUserByTag(@PathVariable("tag")int tag) throws JsonProcessingException {
        UserEntity user=new UserEntity();
        if(tag==1){
            user.setUserName("赵云");
            user.setPassword("1234");
        }
        if(tag==2){
            user.setUserName("马超");
            user.setPassword("2345");
        }
        ObjectMapper mapper=new ObjectMapper();
       String jsonStr= mapper.writeValueAsString(user);

        return jsonStr;
    }
    @RequestMapping("getUserByName")
    public UserEntity getUserByName(@RequestBody String userName){

        UserEntity user=new UserEntity();
        if(userName.equals("赵云")){
            user.setUserName("赵云");
        }
        if(userName.equals("马超")){
            user.setUserName("马超");
        }
        return user;
    }
    @RequestMapping("getUser")
    public UserEntity getUser(@RequestBody UserEntity user){

        System.out.println(user.getUserName());
        return user;
    }
}
