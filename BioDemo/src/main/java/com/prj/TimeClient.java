package com.prj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/25 10:34
 * @Version 1.0
 **/
public class TimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(Objects.nonNull(args) && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        BufferedReader  in = null;
        PrintWriter out = null;
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("Now is :"+resp);
        }finally {
            if(Objects.nonNull(in)){
                try {
                    in.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(Objects.nonNull(out)){
                out.close();
                out = null;
            }

            if(Objects.nonNull(socket)){
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                socket = null;
            }

        }
    }
}
