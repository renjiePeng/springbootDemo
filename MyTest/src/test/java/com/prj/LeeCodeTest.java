package com.prj;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * @className: com.prj.Test
 * @description:
 * @author: pengrj
 * @create: 2022-11-03 9:17
 */
public class LeeCodeTest {

    /**
     * 两数之和
     */
    @Test
    public void test1() {
        int target = 6;
        int[] nums = new int[]{4, 2, 7};
        twoSum2(nums, target);
    }

    /**
     * 判断回文
     */
    @Test
    public void test2() {
        int num = 8787878;
        System.out.println(isPalindrome2(num));
    }


    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char charAt = chars[i];
            for (int y = chars.length - 1 - i; y < chars.length; y++) {
                char innerCharAt = chars[y];
                if (Objects.equals(charAt, innerCharAt)) {
                    break;
                }
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int length = chars.length / 2;
        for (int i = 0; i < length; i++) {
            int currentChar = chars[i];
            length = chars.length == 3 ? 2 : length;
            for (int y = length + i; y < chars.length; ) {
                int afterLengthChar = chars[y];
                if (currentChar == afterLengthChar) {
                    break;
                }
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int length = chars.length / 2;
        for (int i = 0; i < length; i++) {
            int currentChar = chars[i];
            if(currentChar==chars[chars.length-i-1]){
                continue;
            }
            return false;
        }
        return true;
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int mins = target - currentNum;
            for (int y = 0; y < nums.length; y++) {
                if (i == y) {
                    continue;
                }
                if (mins == nums[y]) {
                    result[0] = y;
                    result[1] = i;
                    System.out.println(y + " " + i);
                    return result;
                }
            }
        }
        return result;
    }

    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            for (int y = i + 1; y < nums.length; y++) {
                int tmpNum = nums[y];
                if (currentNum + tmpNum == target) {
                    result[0] = y;
                    result[1] = i;
                    System.out.println(y + " " + i);
                    return result;
                }
            }
        }
        return result;
    }

    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int mins = target - currentNum;
            if (map.containsKey(mins)) {
                Integer index = map.get(mins);
                result[0] = index;
                result[1] = i;
                System.out.println(index + " " + i);
                return result;
            }
            map.put(currentNum, i);
        }
        return result;
    }
}
