package com.lovo.jedis;

public class PersonRun extends  Thread{
    private  String userName; //用户名
    private  int sleepNum=0; //休眠时间
    public  PersonRun(String userName,int sleepNum){
        this.userName=userName;
        this.sleepNum=sleepNum;
    }


    public void run() {
        int runNum=1;
         while (true){
             try {
                 Thread.sleep(sleepNum);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             //模拟跑步
              JedisUtil jedisUtil=new JedisUtil();
            jedisUtil.zincrby("run",runNum,userName);
             runNum++;
         }
    }
}
