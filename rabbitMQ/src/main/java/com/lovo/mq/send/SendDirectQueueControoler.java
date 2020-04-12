package com.lovo.mq.send;

import com.lovo.mq.entity.UserEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SendDirectQueueControoler{

     //注入MQ模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    RabbitTemplate.ConfirmCallback confirmCallback=new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.out.println("correlationData="+correlationData);
            System.out.println("b="+b);
            System.out.println("s="+s);
        }
    };

    RabbitTemplate.ReturnCallback returnCallback=new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
            System.out.println("message="+message);
            System.out.println("i="+i);
            System.out.println("s="+s);
            System.out.println("s1="+s1);
            System.out.println("s2="+s2);
        }
    };



    @RequestMapping("send")
     public  String send(UserEntity user){
        rabbitTemplate.setMandatory(true);//确认消息到达交换机
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        String uuid= System.currentTimeMillis()+"";
        CorrelationData correlationData=new CorrelationData(uuid);
        //向队列中发送消息
        rabbitTemplate.convertAndSend("myFirstDirect","j173",user,correlationData);
         return "发送的消息为："+user;
     }
     @RequestMapping("topic")
     public  String topic(){
         rabbitTemplate.setMandatory(true);//确认消息到达交换机
         rabbitTemplate.setConfirmCallback(confirmCallback);
         rabbitTemplate.setReturnCallback(returnCallback);
         String uuid= System.currentTimeMillis()+"";
         CorrelationData correlationData=new CorrelationData(uuid);
       rabbitTemplate.convertAndSend("myfanoutExchange","","mytopic",correlationData);
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
