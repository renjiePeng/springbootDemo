package com.prj.config;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Author : prj
 * @create 2022/4/14 14:14
 *
 */
public class CopyBeans {
    /**
     * copy对象
     * 传参举例：class,Clazz::new
     * @param source 原始对象
     * @param target 目标对象
     * @param <T> 目标对象
     * @param <S> 原始对象
     * @return 目标对象实例
     */
    public static  <T,S> T copyBean(S source, Supplier<T> target){
        T t = target.get();
        BeanUtils.copyProperties(source,t);
        return t;
    }

    public static <T,S> List<T> copyBean(List<S> sources, Supplier<T> target){
        return sources.stream().map(x->copyBean(x,target)).collect(Collectors.toList());
    }
}
