package com.prj;

/**
 * @PackageName: com.prj
 * @Author 彭仁杰
 * @Date 2023/4/29 14:59
 * @Description
 **/
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if(args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer,"NIO-MulitplexerTimeServer-001").start();
    }
}
