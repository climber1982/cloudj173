package com.lovo;

import java.util.concurrent.ArrayBlockingQueue;

public class AddThread extends  Thread{

    ArrayBlockingQueue arrayBlockingQueue;

    public AddThread(ArrayBlockingQueue arrayBlockingQueue){
        this.arrayBlockingQueue=arrayBlockingQueue;
    }

    public  void run(){
       for(int i=0;i<100;i++){
           //向队列中添加
           try {
               System.out.println("放"+i);
               arrayBlockingQueue.put(i);
               Thread.sleep(500);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

}
