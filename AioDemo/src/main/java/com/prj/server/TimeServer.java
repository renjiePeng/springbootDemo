package com.prj.server;

import java.util.Objects;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/5/6 19:12
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

        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler,"AIO-AsyncTimeServerHandler-001").start();
    }
}
