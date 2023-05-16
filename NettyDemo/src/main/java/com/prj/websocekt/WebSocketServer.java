package com.prj.websocekt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Objects;

/**
 * @PackageName: com.prj.websocekt
 * @Author 彭仁杰
 * @Date 2023/5/13 16:31
 * @Description
 **/
public class WebSocketServer {
    public void run(int port) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //请求和应答消息编码或者解码为http消息。
                            pipeline.addLast("http-codec", new HttpServerCodec());
                            //将http消息中的多个部分组合成一条完整的Http消息。
                            pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                            //向客户端发送HTML5文件，用于支持浏览器与服务端进行通信
                            pipeline.addLast("http-chunked",new ChunkedWriteHandler());
                            pipeline.addLast("handler", new WebSocketServerHandler());
                        }
                    });
            Channel channel = b.bind(port).sync().channel();
            System.out.println("Web socket server started at port "+port + ".");
            System.out.println("Open your brower and navigate to http://localhost:" + port +"/");
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if(Objects.nonNull(args) && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new WebSocketServer().run(port);
    }
}
