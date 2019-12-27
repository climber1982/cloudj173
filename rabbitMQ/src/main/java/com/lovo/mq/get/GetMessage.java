package com.lovo.mq.get;

import com.lovo.mq.entity.UserEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class GetMessage {
    @RabbitListener(queues = "myqueuej173")
    public  void getMessage(UserEntity user){
        System.out.println(user.getUserName());
    }
    @RabbitListener(queues = "queueOne")
    public void getQueueOne(String str){
        System.out.println("我是第一个队列"+str);
    }
    @RabbitListener(queues = "queueTwo")
    public void getQueueTwo(String str){
        System.out.println("我是第二个队列"+str);
    }
}
