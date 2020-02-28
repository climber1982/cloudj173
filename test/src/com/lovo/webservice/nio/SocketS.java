package com.lovo.webservice.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class SocketS {
    public static void main(String[] args) throws Exception {
        //�����ʼ��
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        //����Ϊ������
        serverSocket.configureBlocking(false);
        //�󶨶˿�
        serverSocket.bind(new InetSocketAddress("localhost", 23000));
        //ע��OP_ACCEPT�¼������������¼�������пͻ��˷�������������ü���select()��ѡ�У�
        Selector selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("����˿�����");
          while (true) {
              int num = selector.select();
              if (num > 0) {
                  Set<SelectionKey> setkey = selector.selectedKeys();
                  Iterator<SelectionKey> iterator = setkey.iterator();

                  while (iterator.hasNext()) {
                      SelectionKey key = iterator.next();


                          if(key.isAcceptable()){
                              ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                              SocketChannel socketChannel = serverSocketChannel.accept();
                              if(socketChannel!=null){
                                  //���ŵ�һ��ע��Ϊ��������
                                  socketChannel.configureBlocking(false);
                                  socketChannel.register(key.selector(), SelectionKey.OP_READ);
                              }
                          }else  if(key.isReadable()){
                              while (true) {
                                  key.interestOps(SelectionKey.OP_READ);
                                  SocketChannel channel = (SocketChannel) key.channel();
                                  ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                  channel.read(byteBuffer);
                                  System.out.println(new String(byteBuffer.array()));
                                  key.interestOps(SelectionKey.OP_WRITE);
                                  ByteBuffer byteBufferw = Charset.forName("utf-8").encode("aaa");
                                  channel.write(byteBufferw);
                                         Thread.sleep(1000*5);
                              }
                          }

                      iterator.remove();
                  }

              }
          }
    }
}
