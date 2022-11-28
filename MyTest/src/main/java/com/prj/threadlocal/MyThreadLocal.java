package com.prj.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @PackageName: com.prj.threadlocal
 * @Author 彭仁杰
 * @Date 2022/11/22 21:16
 * @Description
 **/
public class MyThreadLocal<T> {
    private Map<Thread,T> container = Collections.synchronizedMap(new HashMap<Thread,T>());

    public void set(T value){
        container.put(Thread.currentThread(),value);
    }

    public T get(){
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if(Objects.isNull(value) && !container.containsKey(value)){
            value = initialValue();
            container.put(thread,value);
        }
        return value;
    }

    private void remove(){
        container.remove(Thread.currentThread());
    }

    private T initialValue() {
        return null;
    }
}
