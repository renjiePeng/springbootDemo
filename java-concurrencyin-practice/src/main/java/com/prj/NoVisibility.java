package com.prj;

/**
 * @PackageName: com.prj
 * @Author 彭仁杰
 * @Date 2022/11/28 22:21
 * @Description
 **/
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number=42;
        ready=true;
    }
}
