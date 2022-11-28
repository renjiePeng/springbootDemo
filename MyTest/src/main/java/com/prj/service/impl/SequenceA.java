package com.prj.service.impl;

import com.prj.service.Sequence;

/**
 * @PackageName: com.prj.service.impl
 * @Author 彭仁杰
 * @Date 2022/11/22 20:40
 * @Description
 **/
public class SequenceA implements Sequence {

    public static int number = 0;

    @Override
    public int getNumber() {
        number = number+1;
        return number;
    }

    public static void main(String[] args) {
        SequenceA sequenceA = new SequenceA();
        ClientThread clientThread1 = new ClientThread(sequenceA);
        ClientThread clientThread2 = new ClientThread(sequenceA);
        ClientThread clientThread3 = new ClientThread(sequenceA);
        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
