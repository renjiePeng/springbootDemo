package com.prj.echo.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/19 19:50
 * @Version 1.0
 **/
public class EchoClientHandler extends ChannelHandlerAdapter {

    private int counter;

    static final String ECHO_REQ = "Hi,Lilinfeng. Welcome to Netty.$_";

    public EchoClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is "+ ++counter+ " times receive server: ["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常时，记录错误并关闭Channel
        cause.printStackTrace();
        ctx.close();
    }
}
