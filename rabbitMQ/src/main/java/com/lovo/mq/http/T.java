package com.lovo.mq.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class T {
    public static void main(String[] args) {
        HttpClient client= HttpClients.createDefault();
        HttpGet get=new HttpGet("https://baijiahao.baidu.com/s?id=1658305480478968604&wfr=spider&for=pc");
        try {
            HttpResponse response= client.execute(get);
        HttpEntity entity= response.getEntity();
        String str=    EntityUtils.toString(entity,"GBK");
        Document document= Jsoup.parse(str);
            Elements el =document.select("span[class=bjh-p]");
            System.out.println(el.first().text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
