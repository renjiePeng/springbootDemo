package com.prj.time.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/5/7 15:00
 * @Version 1.0
 **/
@Slf4j
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    //private final ByteBuf firstMessage;

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER"+System.lineSeparator()).getBytes();
        //firstMessage = Unpooled.buffer(req.length);
        //firstMessage.writeBytes(req);
    }

    /**
     * 当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用channelActive方法，
     * 发送查询时间的指令到服务端，调用ChannelHandlerContext的writeAndFlush方法将请求 消息
     * 发送给服务端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    /**
     * 读取服务端发送过来的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf buf = (ByteBuf)msg;
        //byte[] req = new byte[buf.readableBytes()];
        //buf.readBytes(req);
        //String body = new String(req, StandardCharsets.UTF_8);
        String body = (String)msg;
        System.out.println("Now is : "+body+" ; the counter is : "+ ++counter);
    }

    /**
     * 如果有异常，捕捉，关闭ChannelHandlerContext
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected execption from downstream : "+cause.getMessage());
        ctx.close();
    }
}
