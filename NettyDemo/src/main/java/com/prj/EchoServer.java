package com.prj;

import com.prj.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/17 9:56
 * @Version 1.0
 **/
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage:" + EchoServer.class.getSimpleName() + " ");
        }
        //设置端口值（如果端口参数的格式不正确，则抛出一个NumberFormatException）
        int port = Integer.parseInt(args[0]);
        //调用服务器的start方法
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        EchoServerHandler serverHandler = new EchoServerHandler();
        //1、创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2、创建ServerBootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class) //3、指定指定的NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) //4、使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer() {  //5、添加一个EchoServerHandler到channel的ChannelPipeline
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            //EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例。
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //6、异步的绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = serverBootstrap.bind().sync();
            //7、获取Channel的closeFuture，并且阻塞当前线程直到它完成。
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭NioEventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
