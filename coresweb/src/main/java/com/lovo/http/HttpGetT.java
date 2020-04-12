package com.lovo.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpGetT {
    public static void main(String[] args) {
        //创建了HttpClient对象
        HttpClient httpClient= HttpClients.createDefault();
        //创建GET请求对象
        HttpGet httpGet=new HttpGet("http://127.0.0.1:8001/info");
        //调用服务器
        try {
            HttpResponse httpResponse= httpClient.execute(httpGet);
            //获得response返回的数据
         HttpEntity httpEntity= httpResponse.getEntity();
         //把HttpEntity转换为string
        String resultStr=    EntityUtils.toString(httpEntity,"UTF-8");
            System.out.println(resultStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
