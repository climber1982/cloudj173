package com.lovo.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    //注入工厂
    @Autowired
    private CachingConnectionFactory connectionFactory;
  //创建交换机
    @Bean
    public DirectExchange createDirectExchange(){
        return  new DirectExchange("myFirstDirect");
    }

    //创建队列
    @Bean
    public Queue createQueue(){
        return  new Queue("myqueuej173");
    }

    //绑定队列
    @Bean
    public Binding bindingDirectExchange(Queue createQueue,DirectExchange createDirectExchange){
        return BindingBuilder.bind(createQueue).to(createDirectExchange).with("j173");
    }
   @Bean
    public RabbitTemplate createRabbitTemplate(){

       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

       return rabbitTemplate;

   }


}
