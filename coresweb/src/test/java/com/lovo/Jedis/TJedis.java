package com.lovo.Jedis;

import com.lovo.UserEntity;
import com.lovo.jedis.JedisUtil;
import com.lovo.jedis.PersonRun;
import com.lovo.jedis.SerializeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TJedis {
    JedisUtil jedisUtil=null;

    @Before
    public  void before(){
       jedisUtil=new JedisUtil();

    }

  @Test
    public  void testByte(){
      //创建对象
      UserEntity userEntity=new UserEntity();
      userEntity.setUserName("赵云");
      userEntity.setAge(28);
      //把对象序列化
     byte[] userByte= SerializeUtil.serialize(userEntity);
     //把序列化后的数据放入到redis
      jedisUtil.setByte("user".getBytes(),userByte);

    }

    @Test
    public  void getUser(){
     UserEntity userEntity= (UserEntity) SerializeUtil.unserizlize
             (jedisUtil.getByte("user".getBytes()));
        System.out.println(userEntity.getUserName()+"/"+userEntity.getAge());
    }

    @Test
    public  void setHash(){
        Map<String,String> map=new HashMap<>();
        map.put("userName","赵云");
        map.put("age","30");
        jedisUtil.setHash("userHash",map);
    }
    @Test
    public  void getHash(){
      String userName=  jedisUtil.getHash("userHash","userName");
      int age=Integer.parseInt(jedisUtil.getHash("userHash","age"));
        System.out.println(userName+"/"+age);
    }


    @Test
    public void setQueue(){
        jedisUtil.setQueue("userQueue",1,"a","b","c");
    }

    @Test
    public void getQueue(){
        int i=1;
        while (true) {
            String str = jedisUtil.getQueue("userQueue", 0);
            System.out.println(str+"取第"+i);
            i++;
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public  void sadd(){
        jedisUtil.sadd("a_array","a","b","d","1","100");
        jedisUtil.sadd("b_array","f","b","","1","1000");
    }
@Test
    public  void sdiff(){
      Set<String> set=  jedisUtil.sdiff("a_array","b_array");
      for (String s:set){
          System.out.println(s);
      }
    }

    @Test
    public  void zadd(){
        Map<String,Double> map=new HashMap<>();
        map.put("赵云",10d);
        map.put("马超",20d);
        map.put("林冲",5d);
        map.put("鲁智深",40d);
        jedisUtil.zadd("run",map);

    }

    @Test
    public void printRun(){
        //开始运动
        PersonRun zy=new PersonRun("赵云",1000);
        PersonRun mc=new PersonRun("马超",2000);
        PersonRun lc=new PersonRun("林冲",1000);
        PersonRun lzs=new PersonRun("鲁智深",3000);
        zy.start();
        mc.start();
        lc.start();
        lzs.start();
        int i=1;
        while (true){
            //从高到底输出跑步排行
            Set<String> set=   jedisUtil.zrevrange("run");
            System.out.println("第"+i+"排行榜");
            for (String str:set){
                System.out.println(str);
            }
            i++;
            try {
                Thread.sleep(1000*6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    public void publist(){
        jedisUtil.publish("message1","hello pub");
    }
}
