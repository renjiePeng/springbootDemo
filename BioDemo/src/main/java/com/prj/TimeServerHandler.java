package com.prj;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/24 17:33
 * @Version 1.0
 **/

public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("当前线程："+Thread.currentThread().getId()+" name: "+Thread.currentThread().getName());
        BufferedReader  in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while(true){
                body = in.readLine();
                if(Objects.isNull(body)){
                    break;
                }
                System.out.println("The time server receive order :"+body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString():"BAD ORDER"; out.println(currentTime);
            }
        } finally {
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

            if(Objects.nonNull(this.socket)){
                try {
                    this.socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
