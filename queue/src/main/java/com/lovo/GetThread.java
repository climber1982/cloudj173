package com.lovo;

import java.util.concurrent.ArrayBlockingQueue;

public class GetThread extends  Thread {
    ArrayBlockingQueue arrayBlockingQueue;
    public  GetThread(ArrayBlockingQueue arrayBlockingQueue){
        this.arrayBlockingQueue=arrayBlockingQueue;
    }

    public  void run(){
        while (true){
            try {
                System.out.println("取"+arrayBlockingQueue.take());
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
