package com.lovo.cores.controller;

import com.lovo.cores.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoresController {

   @RequestMapping("info")
    public  User info(){
       User u=new User();
       u.setUserName("赵云");
       return  u;
    }
}
