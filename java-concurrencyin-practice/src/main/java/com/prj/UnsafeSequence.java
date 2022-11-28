package com.prj;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @PackageName: com.prj.UnsafeSequence
 * @Author 彭仁杰
 * @Date 2022/11/23 15:27
 * @Description
 **/
public class UnsafeSequence {

    AtomicInteger atomicInteger = new AtomicInteger(1);

    private int value = 9;

    public synchronized int getNext(){
        return ++value;
    }

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        Thread thread1 = new Thread(myThread);
        thread.start();
        thread1.start();

    }
}

class MyThread implements Runnable{
    UnsafeSequence unsafeSequence = new UnsafeSequence();
    @Override
    public void run() {
        System.out.println(unsafeSequence.getNext());
    }
}