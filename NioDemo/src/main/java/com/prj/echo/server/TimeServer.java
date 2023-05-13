package com.prj.echo.server;

import java.util.Objects;

/**
 * @Description 使用java原生的Nio
 * @Author pengrj
 * @Date 2023/5/5 16:42
 * @Version 1.0
 **/
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if(Objects.nonNull(args) && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
