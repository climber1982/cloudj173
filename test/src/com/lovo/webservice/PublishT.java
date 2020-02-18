package com.lovo.webservice;

import javax.xml.ws.Endpoint;

public class PublishT {

    public static void main(String[] args) {
        //发布的接口
        IPublishMessage publishMessage=new PublishMessage();
        Endpoint.publish("http://127.0.0.1:8888/publishInfo",publishMessage);
    }
}
