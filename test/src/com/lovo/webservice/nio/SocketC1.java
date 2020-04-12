package com.lovo.webservice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class SocketC1 {
    public static void main(String[] args) throws Exception {
        SocketChannel sc=SocketChannel.open();
        sc.configureBlocking(false);
        Selector selector=Selector.open();
       sc.connect(new InetSocketAddress("localhost",23000));
        sc.register(selector, SelectionKey.OP_CONNECT);
         while (true){
             int num=  selector.select();

           if(num>0) {
               Set<SelectionKey> selectionKeys= selector.selectedKeys();
               Iterator<SelectionKey> iterator=selectionKeys.iterator();
               while (iterator.hasNext()) {
                      SelectionKey key= iterator.next();
                   SocketChannel sckey = (SocketChannel) key.channel();

                   boolean ble = sckey.finishConnect();
                   if(ble) {
                       while (true) {
                           key.interestOps(SelectionKey.OP_WRITE);
                           System.out.println(ble);
                           ByteBuffer byteBuffer = Charset.forName("utf-8").encode("who");
                           sckey.write(byteBuffer);
                           key.interestOps(SelectionKey.OP_READ);
                           ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                           int len = sckey.read(readBuffer);
                           System.out.println(len);
                           System.out.println(new String(readBuffer.array()));
                           Thread.sleep(1000*5);
                       }
                   }
                   iterator.remove();
               }
           }
         }
    }
}
