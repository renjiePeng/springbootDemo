package com.prj.time.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @Description 用于对网络事件进行读写操作
 * @Author pengrj
 * @Date 2023/5/7 14:21
 * @Version 1.0
 **/
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        //ByteBuf类似于java NIO中的ByteBuffer，将消息转为ByteBuf
        //ByteBuf buf = (ByteBuf)msg;
        //byte[] req = new byte[buf.readableBytes()];
        //buf.readBytes(req);
        //String body = new String(req, StandardCharsets.UTF_8).substring(0,req.length-System.getProperty("line.separator").length());
        String body = (String) msg;
        System.out.println("The time server receive order : "+body+ " ; the counter is : "+ ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
