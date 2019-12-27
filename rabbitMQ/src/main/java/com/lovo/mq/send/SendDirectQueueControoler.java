package com.lovo.mq.send;

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
     public  String send(String message){
        //向队列中发送消息
        rabbitTemplate.convertAndSend("myFirstDirect","j173",message);
         return "发送的消息为："+message;
     }
}
