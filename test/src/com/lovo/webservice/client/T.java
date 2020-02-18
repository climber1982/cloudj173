package com.lovo.webservice.client;

public class T {
    public static void main(String[] args) {
        PublishMessageService ps=new PublishMessageService();
     PublishMessage pm=   ps.getPublishMessagePort();
   String str=  pm.getInfo(1);
        System.out.println(str);

    }
}
