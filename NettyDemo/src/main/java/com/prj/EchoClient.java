package com.prj;

import com.prj.client.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/20 10:16
 * @Version 1.0
 **/
public class EchoClient {
    private final String host;

    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //指定EventLoopGroup用来处理客户端事件，需要适用NIO的实现
            bootstrap.group(group)
                    //适用于NIO传输的Channel类型
                    .channel(NioSocketChannel.class)
                    //设置服务器的InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host,port))
                    //在创建Channel时，向ChannelPipeline中添加一个EchoClientHandler实例
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //连接到远程节点
            ChannelFuture f = bootstrap.connect().sync();
            //阻塞，直接Channel关闭
            f.channel().closeFuture().sync();
        } finally {
            //关闭线程池并且释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
//        if(args.length!=2){
//            System.out.println("Usage:"+EchoClient.class.getSimpleName()+" <host> <port>");
//            return;
//        }
        String host = "localhost";
        int port = Integer.parseInt("9999");
        new EchoClient(host,port).start();

    }
}
