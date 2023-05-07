package com.prj.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/5/6 19:50
 * @Version 1.0
 **/
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if(Objects.isNull(this.channel)){
            this.channel = channel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, StandardCharsets.UTF_8);
            System.out.println("The time server receive order : "+body);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req)? new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            doWrite(currentTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String currentTime) throws IOException {
        if(Objects.nonNull(currentTime) && currentTime.trim().length()>0){
            byte[] bytes = currentTime.getBytes(StandardCharsets.UTF_8);
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    //如果没有发送完成，急需发送
                    if(buffer.hasRemaining()){
                        channel.write(buffer,buffer,this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }
}
