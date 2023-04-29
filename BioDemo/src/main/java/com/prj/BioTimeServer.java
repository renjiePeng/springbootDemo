package com.prj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @Description BIO的I/O模型
 * @Author pengrj
 * @Date 2023/4/24 17:28
 * @Version 1.0
 **/
public class BioTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(Objects.nonNull(args) && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Socket socket = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: "+port);
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
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
