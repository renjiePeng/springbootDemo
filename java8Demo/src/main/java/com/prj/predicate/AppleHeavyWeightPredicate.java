package com.prj.predicate;

/**
 * @className: com.prj.predicate.AppleHeavyWeightPredicate
 * @description:
 * @author: pengrj
 * @create: 2022-10-28 10:51
 */
public class AppleHeavyWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 12;
    }
}
