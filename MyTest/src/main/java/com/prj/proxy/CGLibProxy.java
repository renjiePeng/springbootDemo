package com.prj.proxy;

import com.prj.service.impl.HelloImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @PackageName: com.prj.proxy
 * @Author 彭仁杰
 * @Date 2022/11/21 18:10
 * @Description
 **/
public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance = new CGLibProxy();

    public CGLibProxy() {
    }

    public static CGLibProxy getInstance(){
        return instance;
    }

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(obj, args);
        after();
        return result;
    }

    public void before(){
        System.out.println("Before");
    }

    public void after(){
        System.out.println("After");
    }

    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        HelloImpl proxy = cgLibProxy.getProxy(HelloImpl.class);
//        proxy.say("GDD");
        HelloImpl proxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        proxy.say("GDD");
    }
}
