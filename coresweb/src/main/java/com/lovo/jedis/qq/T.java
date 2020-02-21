package com.lovo.jedis.qq;

import com.lovo.jedis.JedisUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class T {
    public static void main(String[] args) {
        //两个QQ好友
        Map<String,String> qq1=new HashMap<>();
        Map<String,String> qq2=new HashMap<>();

        //qq中添加好友
        qq1.put("6666","赵云");
        qq1.put("2222","刘备");
        qq1.put("1000","马超");
        qq1.put("8888","林冲");

        qq2.put("6666","赵云");
        qq2.put("7777","关羽");
        qq2.put("9999","张飞");
        qq2.put("8888","林冲");
        //显示共同好友，其实就是求交集
        //1、取出QQ1的QQ号码
        int i=0;
        int j=0;
        String[] qq1Num=new String[4];
        String[] qq2Num=new String[4];
        for(String qq:qq1.keySet()){
            qq1Num[i]=qq;
            i++;
        }
        //2、取出QQ2的QQ号码
        for(String qq:qq2.keySet()){
            qq2Num[j]=qq;
            j++;
        }
        //3、把QQ好放入到redis
        JedisUtil jedisUtil=new JedisUtil();
        jedisUtil.sadd("qq1",qq1Num);
        jedisUtil.sadd("qq2",qq2Num);
        //4、在redis中取出qq1和qq2的交集
      Set<String> qq=  jedisUtil.sinter("qq1","qq2");
        //4、显示出QQ号码对应的名字
         i=0;
        for(String qqNum:qq){
         String qqName=   qq1.get(qqNum);
            System.out.println("QQ1和QQ2共同好友:"+i+":"+qqName);
            i++;
        }
        //推荐好友QQ1-QQ2,给QQ2推荐好友
           qq=jedisUtil.sdiff("qq1","qq2");
        //5、显示出QQ号码对应的名字
        i=0;
        for(String qqNum:qq){
            String qqName=   qq1.get(qqNum);
            System.out.println("QQ2推荐好友:"+i+":"+qqName);
            i++;
        }
    }
}
