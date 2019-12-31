package com.lovo.mq.send;

import com.lovo.mq.entity.UserEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendDirectQueueControoler{

     //注入MQ模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("send")
     public  String send(UserEntity user){
        //向队列中发送消息
        rabbitTemplate.convertAndSend("myFirstDirect","j173",user);
         return "发送的消息为："+user;
     }
     @RequestMapping("topic")
     public  String topic(){
       rabbitTemplate.convertAndSend("myfanoutExchange","","mytopic");
        return "ok";
     }
     @RequestMapping("savaUserVue")
     public  String savaUser(String userName){
         rabbitTemplate.convertAndSend("myFirstDirect","j173",userName);
        return "ok";
     }

     @RequestMapping("savaOrder")
    public String savaOrder(String userName){
         rabbitTemplate.convertAndSend("myFirstDirect","j173",userName);
        return "ok";
    }
}
