package com.lovo.jedis.getSubMessage;

import redis.clients.jedis.JedisPubSub;

public class GetMessage1 extends JedisPubSub {


    public void onMessage(String channel, String message) {
        System.out.println("在GetMessage1通道"+channel+":获得的消息为"+message);
    }
}
