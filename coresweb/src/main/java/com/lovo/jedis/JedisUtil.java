package com.lovo.jedis;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * jedis工具类
 */
public class JedisUtil {
    private  String ip="127.0.0.1";
    private int port=8001;

    public JedisUtil(){}
    public JedisUtil(String ip,int port){
          this.ip=ip;
          this.port=port;
    }

    /**
     * 获得redis对象
     * @return 获得redis对象
     */
    public   Jedis createJedis(){
        Jedis jedis=new Jedis(ip,port);
        //jedis.auth(""); //密码认证
        return  jedis;
    }
    /**
     * 获得redis对象
     * @param ip  连接redis的IP地址
     * @param port  连接redis的端口号
     * @return 获得redis对象
     */
    public   Jedis createJedis(String ip,int port){
        Jedis jedis=new Jedis(ip,port);
        return  jedis;
    }

    /**
     * 保存字符串
     * @param key
     * @param val
     */
    public  void setString(String key,String val){

          createJedis().set(key,val);

    }

    /**
     * 保存二进制
     * @param key
     * @param val
     */
    public  void setByte(byte[] key,byte[] val){
        createJedis().set(key,val);
    }

    /**
     * 获取二进制
     * @param key
     * @return
     */
    public byte[] getByte(byte[] key){
        return  createJedis().get(key);
    }

    /**
     * 根据key获得字符串
     * @param key
     * @return
     */
    public String getString(String key){
        return createJedis().get(key);
    }

    /**
     * 根据key 来删除
     * @param key
     */
    public void del(String key){
      createJedis().del(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param val
     * @param time
     */
    public void setexBasic(String key,String val,int time){
          createJedis().setex(key,time,val);
    }


    /**
     * 设置过期时间为二进制数据
     * @param key
     * @param time
     * @param val
     */
    public  void setexByte(byte[] key,int time,byte[]val){
           createJedis().setex(key,time,val);
    }

    /**
     * hash的设值
     * @param key
     * @param map
     */
    public void setHash(String key, Map<String,String> map){
         createJedis().hmset(key,map);
    }

    /**
     * 获得hash的值
     * @param key
     * @param field
     * @return
     */
    public String getHash(String key,String field){
        return  createJedis().hget(key,field);
    }

    /**
     * 向队列中放数据
     * @param key
     * @param strings
     * @param tag  0-左边，1-右边
     */
    public  void setQueue(String key,int tag,String...strings){
       if(tag==0){
           //左边放
           createJedis().lpush(key,strings);
       }else if(tag==1){
           //右边放
           createJedis().rpush(key,strings);
       }
    }

    /**
     * 从队列中获取
     * @param key
     * @param tag
     * @return
     */
    public  String getQueue(String key,int tag){
        if(tag==0){
            //左边放
         return    createJedis().lpop(key);
        }else if(tag==1){
            //右边放
            return    createJedis().rpop(key);
        }
        return "";
    }

    /**
     * 添加集合
     * @param key
     * @param strings
     */
    public void sadd(String key,String... strings){
       Jedis jedis=createJedis();
       jedis.sadd(key,strings);
       jedis.close();
    }

    /**
     * 获取差集
     * @param strings
     * @return
     */
    public Set<String> sdiff(String ...strings){
        Jedis jedis=createJedis();
        Set<String>  set=   jedis.sdiff(strings);
        jedis.close();
        return  set;
    }
    /**
     * 获取交集
     * @param strings
     * @return
     */
    public Set<String> sinter(String ...strings){
        Jedis jedis=createJedis();
        Set<String>  set=   jedis.sinter(strings);
        jedis.close();
        return  set;
    }

}
