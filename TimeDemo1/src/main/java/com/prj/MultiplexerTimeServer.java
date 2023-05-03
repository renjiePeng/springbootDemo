package com.prj;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @PackageName: com.prj
 * @Author 彭仁杰
 * @Date 2023/4/29 15:01
 * @Description
 **/
public class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //channel设置为异步非阻塞模式
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Time server is start in port:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        this.stop = true;
    }


    @Override
    public void run() {
        while(!stop){
            try {
                //selector 每隔一秒唤醒一次
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while(iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if(Objects.nonNull(key)){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源。
        if(Objects.nonNull(selector)){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                //Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //Add the new connection to the selector
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                //Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes>0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("The time server receive order :"+body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readBytes < 0){
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                }else{
                    ;//读取到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if(Objects.nonNull(response) && response.trim().length()>0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
