package com.prj.proxy;

import com.prj.service.Hello;
import com.prj.service.impl.HelloImpl;

/**
 * @PackageName: com.prj.service.impl
 * @Author 彭仁杰
 * @Date 2022/11/21 17:06
 * @Description
 **/
public class HelloProxy implements Hello {
    @Override
    public void say(String name) {
        before();
        System.out.println(name);
        after();
    }

    private Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    public void before(){
        System.out.println("Before");
    }

    public void after(){
        System.out.println("After");
    }

    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.say("prj");
    }
}
