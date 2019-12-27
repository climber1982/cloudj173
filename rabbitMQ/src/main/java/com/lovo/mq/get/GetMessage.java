package com.lovo.mq.get;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class GetMessage {
    @RabbitListener(queues = "myqueuej173")
    public  void getMessage(String str){
        System.out.println(str);
    }

}
