package com.lovo.jedis.getSubMessage;

import com.lovo.jedis.JedisUtil;
import redis.clients.jedis.Jedis;

public class T2 {
    public static void main(String[] args) {
        JedisUtil jedisUtil=new JedisUtil();
        Jedis jedis= jedisUtil.createJedis();
        //启动第二个监听
        jedis.subscribe(new GetMessage2(),"message1");
    }
}
