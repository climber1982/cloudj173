package com.lovo.jedis.getSubMessage;

import redis.clients.jedis.JedisPubSub;

public class GetMessage2 extends JedisPubSub {

    public void onMessage(String channel, String message) {
        System.out.println("在GetMessage2中通道"+channel+":获得的消息为"+message);
    }
}

