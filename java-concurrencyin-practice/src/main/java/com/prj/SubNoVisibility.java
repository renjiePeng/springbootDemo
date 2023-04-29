package com.prj;

/**
 * @PackageName: com.prj
 * @Author 彭仁杰
 * @Date 2022/12/6 22:12
 * @Description
 **/
public class SubNoVisibility extends NoVisibility{
    public String name = "ppp";

    static {
        System.out.println("加载子类……");
    }

    public static void main(String[] args) {
        SubNoVisibility subNoVisibility = new SubNoVisibility();
        System.out.println(subNoVisibility.name);

        System.out.println(subNoVisibility.test1("pop"));
    }

    @Override
    public String test1(String name) {
        return super.test1(name);
    }
}
