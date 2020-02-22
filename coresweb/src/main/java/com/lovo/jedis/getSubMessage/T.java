package com.lovo.jedis.getSubMessage;

import com.lovo.jedis.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * 监听测试类
 */
public class T {

    public static void main(String[] args) {
        JedisUtil jedisUtil=new JedisUtil();
      Jedis jedis= jedisUtil.createJedis();
        //启动第一个监听
           jedis.subscribe(new GetMessage1(),"message1");

    }
}
