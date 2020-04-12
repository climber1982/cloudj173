package com.lovo.webservice.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer extends Thread{
	public static void main(String[] args) {
		NioServer server = new NioServer("127.0.0.1", 23000);
		server.start();
	}
	private InetSocketAddress address;
	private ByteBuffer readBuffer;
	private Selector selector;
	public NioServer(){}
	public NioServer(String localhost,int port){

		address = new InetSocketAddress(localhost, port);
	}
	private void init(){
		readBuffer = ByteBuffer.allocate(Constants.readCapacity);
		 try {
			selector = Selector.open();
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.bind(address);
			//ע�ᵽ�ŵ���
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			init();
			while(true){
				int num = selector.select();
				if(num>0){
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while(iterator.hasNext()){
						SelectionKey sk = iterator.next();
						handlerKey(sk);
						//ע�⽫���Ƴ�
						iterator.remove();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void handlerKey(SelectionKey sk) {
		
		if(sk.isAcceptable()){
			accept(sk);
		}else if(sk.isReadable()){
			readMsg(sk);
		}
		
	}
	public void accept(SelectionKey sk) {
		try {
			ServerSocketChannel serverChannel	= (ServerSocketChannel)sk.channel();
			SocketChannel channel = serverChannel.accept();
			if(channel!=null){
				//���ŵ�һ��ע��Ϊ��������
				channel.configureBlocking(false);
				channel.register(sk.selector(), SelectionKey.OP_READ);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void readMsg(SelectionKey sk) {
		SocketChannel channel = null;
		try {
			channel = (SocketChannel) sk.channel();
			readBuffer.clear();
			int num = channel.read(readBuffer);
			if(num>0){
				//������ȡ����
				readBuffer.flip();
				System.out.println("���ܵ�������:"+num+"�ֽ�");
				CharBuffer buffer =  Constants.decoder(readBuffer);
				String recevieMsg = buffer.toString();
				System.out.println(recevieMsg);
				String answer = getAnswer(recevieMsg);
				
				CharBuffer cbuffer = CharBuffer.wrap(answer.toCharArray());
				channel.write(Constants.encoder(cbuffer));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void writeMsg(SelectionKey sk) throws IOException{
		
	}
	private String getAnswer(String question){  
        String answer = null;  
           
        switch(question){  
        case "who":  
            answer = "����С��\n";  
            break;  
        case "what":  
            answer = "������������Ƶ�\n";  
            break;  
        case "where":  
            answer = "��������̫��\n";  
            break;  
        case "hi":  
            answer = "hello\n";  
            break;  
        case "bye":  
            answer = "88\n";  
            break;  
        default:  
                answer = "������ who�� ����what�� ����where";  
        }  
           
        return answer;  
    }  
}
