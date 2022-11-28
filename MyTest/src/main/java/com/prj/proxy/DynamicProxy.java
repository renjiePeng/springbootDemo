package com.prj.proxy;

import com.prj.service.Hello;
import com.prj.service.impl.HelloImpl;

import java.lang.reflect.*;

/**
 * @PackageName: com.prj.proxy
 * @Author 彭仁杰
 * @Date 2022/11/21 17:58
 * @Description
 **/
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }


    public void before(){
        System.out.println("Before");
    }

    public void after(){
        System.out.println("After");
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }



    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Hello hello = new HelloImpl();
//        DynamicProxy dynamicProxy = new DynamicProxy(hello);
//        Hello helloProxy = dynamicProxy.getProxy();
//        helloProxy.say("prj");

        Class proxyClass = Proxy.getProxyClass(Hello.class.getClassLoader(), Hello.class);
        Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
        Hello he = (Hello)constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HelloImpl hello = new HelloImpl();
                Object result = method.invoke(hello, args);
                return result;
            }
        });
        he.say("prj");
    }
}
