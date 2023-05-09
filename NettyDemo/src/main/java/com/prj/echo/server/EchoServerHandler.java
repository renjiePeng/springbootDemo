package com.prj.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/16 19:29
 * @Version 1.0
 **/
public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    /**
     *
     *
     * 对于每个传入的消息都要调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("This is "+ ++counter + " times receive client : [" + body + "]");
        body += "$_";
        //将接收到的消息转发给发送者，而不冲刷出站消息。
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    /**
     * 在读取操作期间，有异常抛出时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常的栈信息
        cause.printStackTrace();
        //关闭该Channel
        ctx.close();
    }
}
