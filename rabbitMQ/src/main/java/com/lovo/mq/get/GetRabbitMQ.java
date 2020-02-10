package com.lovo.mq.get;

import com.lovo.mq.entity.UserEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class GetRabbitMQ {

    @RabbitListener(queues = "myqueuej173")
    public  void getMessae(UserEntity user){
        System.out.println("收到消息"+user.getUserName());
    }
}
