package com.prj.predicate;

/**
 * @className: com.prj.predicate.Predicate
 * @description:
 * @author: pengrj
 * @create: 2022-10-28 13:28
 */
public interface Predicate<T> {
    boolean test(T t);
}
