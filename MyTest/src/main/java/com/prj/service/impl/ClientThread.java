package com.prj.service.impl;

import com.prj.service.Sequence;

/**
 * @PackageName: com.prj.service.impl
 * @Author 彭仁杰
 * @Date 2022/11/22 20:37
 * @Description
 **/
public class ClientThread extends Thread{
    private Sequence sequence;

    public ClientThread(Sequence sequence){
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for(int i=0; i<3;i++){
            System.out.println(Thread.currentThread().getName()+"=>"+sequence.getNumber());
        }
    }
}
