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

    public String name = "wew";

    static {
        System.out.println("加载父类……");
    }

    public String test1(String name){
        return name+"father -test1";
    }

    public static String test2(String name){
        return name+"father -test2";
    }

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
