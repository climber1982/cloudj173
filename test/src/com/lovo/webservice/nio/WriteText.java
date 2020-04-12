package com.lovo.webservice.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

public class WriteText {
    public static void main(String[] args) throws Exception {
        FileOutputStream fout=new FileOutputStream("D://test.txt");
        ByteChannel channel=fout.getChannel();
        ByteBuffer byteBuffer= Charset.forName("utf-8").encode("hello nio");
        int len=0;
        while ((len=channel.write(byteBuffer))!=0){
            System.out.println("Ð´Èë×Ö¶Î³¤¶È"+len);
        }
    }
}
