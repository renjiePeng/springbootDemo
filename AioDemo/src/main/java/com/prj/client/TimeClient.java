package com.prj.client;

import java.util.Objects;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/5/6 19:58
 * @Version 1.0
 **/
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if(Objects.nonNull(args) && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new Thread(new AsyncTimeClientHandler("127.0.0.1",port),"AIO-AsyncTimeClientHandler-01").start();
    }
}
