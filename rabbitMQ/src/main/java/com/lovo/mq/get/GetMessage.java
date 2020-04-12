package com.lovo.mq.get;

import com.lovo.mq.entity.UserEntity;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ReturnCallback;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

@Component
public class GetMessage {

    //    @RabbitListener(queues = "myqueuej173")
//    public  void getMessage(UserEntity user){
//        System.out.println(user.getUserName());
//    }
  public   ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<>(10);

    @RabbitListener(queues = "queueOne")
    public void getQueueOne(String str){
        System.out.println("我是第一个队列"+str);
    }
    @RabbitListener(queues = "queueTwo")
    public void getQueueTwo(String str,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag){
        try {
            channel.basicNack(tag,false,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("我是第二个队列"+str);
    }

   @RabbitListener(queues = "myqueuej173")
    public  void getMessage(Channel channel,UserEntity userEntity,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InterruptedException, IOException {

     // channel.basicNack(tag,false,true);

   System.out.println("我是第三个队列："+userEntity.getUserName());

    }
}
