package com.prj.service.impl;

import com.prj.service.Sequence;
import com.prj.threadlocal.MyThreadLocal;

/**
 * @PackageName: com.prj.service.impl
 * @Author 彭仁杰
 * @Date 2022/11/22 20:43
 * @Description
 **/
public class SequenceC implements Sequence {

    private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>(){
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+ 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        SequenceC sequenceC = new SequenceC();
        ClientThread clientThread1 = new ClientThread(sequenceC);
        ClientThread clientThread2 = new ClientThread(sequenceC);
        ClientThread clientThread3 = new ClientThread(sequenceC);
        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
