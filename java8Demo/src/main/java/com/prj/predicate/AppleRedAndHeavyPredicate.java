package com.prj.predicate;

/**
 * @className: com.prj.predicate.AppleRedAndHeavyPredicate
 * @description:
 * @author: pengrj
 * @create: 2022-10-28 11:04
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("red") && apple.getWeight()>2;
    }
}
