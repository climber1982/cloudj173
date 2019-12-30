package com.lovo.mq.send;

import com.lovo.mq.get.GetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

    @Autowired
    GetMessage getMessage;
    @RequestMapping("getUserName")
   public  String getUserName() throws InterruptedException {
        //获得队列的数据
         String str=getMessage.queue.take();
        System.out.println(str);
        return str;

   }
}
