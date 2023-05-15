package com.prj.websocekt;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * @PackageName: com.prj.websocekt
 * @Author 彭仁杰
 * @Date 2023/5/13 16:50
 * @Description
 **/
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger log = Logger.getLogger(WebSocketServerHandler.class.getName());

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        //传统的HTTP传入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            //WebSocket接入
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否是关闭链路的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),(CloseWebSocketFrame)frame.retain());
            return;
        }
        //判断是否是ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //本例程仅支持文本消息，不支持二进制消息，
        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported",frame.getClass().getClass()));
        }

        //返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        if(log.isLoggable(Level.FINER)){
            log.fine(String.format("%s received %s", ctx.channel(),request));
        }
        ctx.channel().write(
                new TextWebSocketFrame(request + " , 欢迎使用Netty WebSocket服务，现在时刻："+new Date().toString())
        );
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (req.getDecoderResult().isSuccess() || (Objects.equals("websocket", req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //构造握手响应，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if(Objects.isNull(handshaker)){
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(),req);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        //返回应答给客户端
        if(res.getStatus().code() != 200){
            ByteBuf byteBuf = Unpooled.copiedBuffer(res.getStatus().toString(), StandardCharsets.UTF_8);
            res.content().writeBytes(byteBuf);
            byteBuf.release();
            setContentLength(res,res.content().readableBytes());
        }

        //如果是非Keep-alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if(!isKeepAlive(req) || res.getStatus().code()!=200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
