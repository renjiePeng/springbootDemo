package com.prj.predicate;

/**
 * @className: com.prj.predicate.AppleGreenColorPredicate
 * @description:
 * @author: pengrj
 * @create: 2022-10-28 10:52
 */
public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
