package com.prj.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @Description  Sharable表示可以被多个Channel共享
 * @Author pengrj
 * @Date 2023/4/16 19:29
 * @Version 1.0
 **/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 对于每个传入的消息都要调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server received:"+byteBuf.toString(StandardCharsets.UTF_8));
        //将接收到的消息转发给发送者，而不冲刷出站消息。
        ctx.write(msg);
    }

    /**
     * 通知ChannelInboundHandler最后一次channel-Read()的调用是当前批量读取中的最后一条消息。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将暂存与ChannelOutboundBuffer中的消息冲刷到远程节点，并且关闭该channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
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
