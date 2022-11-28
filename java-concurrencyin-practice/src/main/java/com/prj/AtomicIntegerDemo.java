package com.prj;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PackageName: com.prj
 * @Author 彭仁杰
 * @Date 2022/11/26 21:21
 * @Description
 **/
public class AtomicIntegerDemo {
    final static AtomicInteger value = new AtomicInteger(10);
    public static void main(String[] args) throws InterruptedException {
        System.out.println(value.addAndGet(5));
//        System.out.println(value.get());
    }

    private void test1() throws InterruptedException{
        //true
        System.out.println("1:"+Objects.equals(value.compareAndSet(1, 2), false));
        //true
        System.out.println("2:"+Objects.equals(value.get(), 10));
        //3
        System.out.println("3:"+(value.compareAndSet(10, 3)));
        //3
        System.out.println("4:"+Objects.equals(value.get(), 3));
        value.set(0);
        //true
        System.out.println("5:"+Objects.equals(value.incrementAndGet(), 1));
        //false
        System.out.println("6:"+Objects.equals(value.getAndAdd(2),1));
        //true
        System.out.println("7:"+Objects.equals(value.getAndSet(5),3));
        //true
        System.out.println("8:"+Objects.equals(value.get(),5));
        //
        final int threadSize = 10;
        Thread[] ts = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    value.incrementAndGet();
                }
            };
        }
        //
        for(Thread t:ts) {
            t.start();
        }
        for(Thread t:ts) {
            t.join();
        }
        //
        System.out.println("最后的value.get():"+value.get());
        System.out.println(Objects.equals(value.get(), 5+threadSize));

        System.out.println("---------------------");
    }
}
