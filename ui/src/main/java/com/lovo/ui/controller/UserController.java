package com.lovo.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.ui.dao.IUserDao;
import com.lovo.ui.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
     @Autowired
    private IUserDao userDao;

     @RequestMapping("getListUser")
     public String getListUser() throws JsonProcessingException {
       List<UserEntity> list= (List<UserEntity>) userDao.findAll();
         ObjectMapper mapper=new ObjectMapper();
         String result=mapper.writeValueAsString(list);
         return result;
     }

     @RequestMapping("updateUser")
     @Transactional
     public  String updateUser(UserEntity user){
           userDao.save(user);
           return "修改成功";
     }

     @RequestMapping("userInfo/{id}")
     public String userInfo(@PathVariable("id")String id) throws JsonProcessingException {
        UserEntity user= userDao.findById(id).get();
         ObjectMapper mapper=new ObjectMapper();
         String result=mapper.writeValueAsString(user);
         return result;
     }
}
