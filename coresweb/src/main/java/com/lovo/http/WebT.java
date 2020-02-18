package com.lovo.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebT {

    public static void main(String[] args) {
        //获得HttpClient对象
        HttpClient httpClient= HttpClients.createDefault();
        //创建Get对象
        HttpGet httpGet=new HttpGet("https://baijiahao.baidu.com/s?id=1658853283619075109&wfr=spider&for=pc");
       //发送请求
        try {
            HttpResponse httpResponse= httpClient.execute(httpGet);
            //获得内容
        String html=   EntityUtils.toString(httpResponse.getEntity(),"GBK");
        //通过jsoup获得HTML的document对象
            Document document= Jsoup.parse(html);
            //通过document对象获取我们想要的元素
            Elements elements= document.select("span[class=bjh-p]");
            System.out.println(elements.first().text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
