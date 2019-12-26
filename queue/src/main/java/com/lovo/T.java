package com.lovo;

import java.util.concurrent.ArrayBlockingQueue;

public class T {
    public static void main(String[] args) {
     //创建一个阻塞式队列
        ArrayBlockingQueue  blockingQueue=new ArrayBlockingQueue(10);
        AddThread addThread=new AddThread(blockingQueue);
        GetThread getThread=new GetThread(blockingQueue);

        //启动
        addThread.start();
        getThread.start();

    }
}
