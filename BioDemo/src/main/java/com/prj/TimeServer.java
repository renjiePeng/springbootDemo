package com.prj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @Description 伪异步的I/O模型
 * @Author pengrj
 * @Date 2023/4/24 17:28
 * @Version 1.0
 **/
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(Objects.nonNull(args) && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: "+port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true){
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        }finally {
            if(Objects.nonNull(server)){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
