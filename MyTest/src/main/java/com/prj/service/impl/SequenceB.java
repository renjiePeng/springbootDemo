package com.prj.service.impl;

import com.prj.service.Sequence;

/**
 * @PackageName: com.prj.service.impl
 * @Author 彭仁杰
 * @Date 2022/11/22 20:43
 * @Description
 **/
public class SequenceB implements Sequence {

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        SequenceB sequenceB = new SequenceB();
        ClientThread clientThread1 = new ClientThread(sequenceB);
        ClientThread clientThread2 = new ClientThread(sequenceB);
        ClientThread clientThread3 = new ClientThread(sequenceB);
        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
