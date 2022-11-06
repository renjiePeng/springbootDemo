package com.prj.predicate;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @className: com.prj.predicate.Domain
 * @description:
 * @author: pengrj
 * @create: 2022-10-28 10:53
 */
public class Domain {
    public final int value = 4;
    public static void main(String[] args) {
        List<Apple> apples = getApple();

        new Domain().doIt();

        //
        List<Apple> appleList = filterAppleTest(apples);


        //使用抽象方式和lambda
        List<Apple> filter = filter(appleList, apple -> apple.getWeight() > 3);
        System.out.println(filter.size());

        //排序
        comparatorTest(apples);


        //用runnable执行代码
        new Domain().runTest();

        //测试process
        new Domain().testProcess();;
    }

    /**
     * 过滤条件测试
     * @param apples
     * @return List<Apple>
     */
    private static List<Apple> filterAppleTest(List<Apple> apples) {
        List<Apple> appleList = filterApple(apples, apple -> apple.getWeight() > 5);
        List<Apple> appleList1 = filterApple(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 5;
            }
        });

        System.out.println(appleList.size());
        System.out.println(appleList1.size());
        return appleList;
    }

    private static void comparatorTest(List<Apple> apples) {
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        apples.sort((o1, o2)->o1.getWeight().compareTo(o2.getWeight()));
        System.out.println(JSON.toJSONString(apples));

        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println("*********************");
        System.out.println(JSON.toJSONString(apples));
    }

    public void process(Runnable r){
        r.run();
    }

    public void testProcess(){
        process(new Runnable() {
            @Override
            public void run() {
                System.out.println("13");
            }
        });
        process(()-> System.out.println("123"));
    }

    /**
     * 下面两种写法一样
     */
    private void runTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        });

        Thread thread1 = new Thread(()->System.out.println("123"));
        thread.run();
        thread1.run();
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> ts = new ArrayList<>();
        for (T t : list) {
            if(predicate.test(t)){
                ts.add(t);
            }
        }
        return ts;
    }

    private void doIt(){
        int value = 6;
        Runnable r = new Runnable(){
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    private static List<Apple> filterApple(List<Apple> inventor, ApplePredicate p){
        ArrayList<Apple> apples = new ArrayList<>();
        for (Apple apple : inventor) {
            if(p.test(apple)){
                apples.add(apple);
            }
        }
        return apples;
    }


//    private static List<Apple> filterApple(List<Apple> inventor, Supplier<ApplePredicate> p){
//        ArrayList<Apple> apples = new ArrayList<>();
//        for (Apple apple : inventor) {
//            if(p.get().test(apple)){
//                apples.add(apple);
//            }
//        }
//        return apples;
//    }

    private static List<Apple> getApple() {
        ArrayList<Apple> apples = new ArrayList<>();
        Apple apple = new Apple();
        apple.setColor("green");
        apple.setWeight(15);

        Apple apple1 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(10);

        apples.add(apple);
        apples.add(apple1);
        return apples;
    }
}
