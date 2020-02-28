package com.lovo.webservice.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class ReadText {

    public static void main(String[] args) throws Exception {
        File f=new File("D://a.txt");
        FileInputStream inputStream=new FileInputStream(f);
        FileChannel channel= inputStream.getChannel();
      ByteBuffer c=  ByteBuffer.allocate(1024);
      int len=-1;
         while ((len=channel.read(c))!=-1){
             c.flip();
             System.out.println(c.position()+":"+c.limit()+":"+c.capacity());
                byte[] bytes=c.array();
                String str=new String(bytes,0,len);
             System.out.println(str);
             c.clear();
         }

        channel.close();
    }

}
