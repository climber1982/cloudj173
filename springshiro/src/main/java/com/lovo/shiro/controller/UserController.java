package com.lovo.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("login")
    @ResponseBody
    public ModelAndView login(String userName,String password){
        ModelAndView mv=new ModelAndView("list");
        //添加用户认证信息
        //获得shiro主题
        Subject subject = SecurityUtils.getSubject();
        //把我们的用户和密码放入到shiro的token对象中
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userName,
                password
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();

        } catch (AuthorizationException e) {
            e.printStackTrace();

        }
        return  mv;
    }
    @RequestMapping("finduser")
    @ResponseBody
    public String finduser(){
        //判断用户是否拥有权限，如果有就返回真否则假
     boolean bl=   SecurityUtils.getSubject().isPermitted("finduser");
     System.out.println(bl);

     if(!bl){
         return "没有权限。。。。。。";
     }

        return "finduser";
    }
    @RequestMapping("finduser2")
    @ResponseBody
    public String finduser2(){
        boolean bl=   SecurityUtils.getSubject().isPermitted("finduser2");
        System.out.println(bl);
        if(!bl){
            return "没有权限。。。。。。";
        }
        return "finduser";
    }
    @RequestMapping("finduser6")
    @ResponseBody           
    public String finduser6(){
        boolean bl=   SecurityUtils.getSubject().isPermitted("finduser6");
        System.out.println(bl);
        if(!bl){
            return "没有权限。。。。。。";
        }
        return "finduser";
    }
}
