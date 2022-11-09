package com.prj;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;

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

    /**
     * 罗马数字转换
     */
    @Test
    public void test3() {
        String s = "XLIX";
        System.out.println(romanToInt(s));
    }

    /**
     * 最长公共前缀
     */
    @Test
    public void test4() {
        String[] strs = {"a"};
        System.out.println(longestCommonPrefix(strs));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     */
    @Test
    public void test5() {
        String s = "{";
        System.out.println(isValid(s));
    }

    public boolean isValid(String s){
        if(s.length()%2!=0){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c=='('){
                stack.push(')');
            }else if(c=='['){
                stack.push(']');
            }else if(c=='{'){
                stack.push('}');
            }else if(stack.isEmpty() || c!=stack.pop()){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        if(s.length()==0){
            return true;
        }

        if(s.length()==1){
            return false;
        }

        boolean flag = true;
        char lastChar = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if(i>0) {
                lastChar = s.charAt(i-1);
            }
            switch (currentChar){
                case '(':
                case '[':
                case '{':
                    flag = false;
                    break;
                case ')':
                    if(lastChar=='(') {
                        flag = true;
                    }
                    break;
                case ']':
                    if(lastChar=='[') {
                        flag = true;
                    }
                    break;
                case '}':
                    if(lastChar=='{') {
                        flag = true;
                    }
                    break;
            }
        }
        return flag;
    }

    public boolean isValid1(String s) {
        if(s.length()==0){
            return true;
        }

        int leftSmailsymbol = 0;
        int leftmediumsymbol = 0;
        int leftBigsymbol = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            switch (currentChar){
                case '(':
                    leftSmailsymbol+=1;
                    break;
                case '[':
                    leftmediumsymbol+=1;
                    break;
                case '{':
                    leftBigsymbol+=1;
                    break;
                case ')':
                    leftSmailsymbol-=1;
                    break;
                case ']':
                    leftmediumsymbol-=1;
                    break;
                case '}':
                    leftBigsymbol-=1;
                    break;
            }
        }
        return leftSmailsymbol == 0 && leftmediumsymbol == 0 && leftBigsymbol == 0;
    }

    /**
     * 1、判断原始数组不为null且长度必须大于0；
     * 2、获取数组第一个元素
     * 3、再挨个遍历每个单词，与后面的元素用startWith判断是否为开头
     * 4、如果都是以该单词开头的，添加到缓存中，否则return。
     * @param strs 原始数据
     * @return 公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (Objects.nonNull(strs) && strs.length == 0) {
            return "";
        }
        String cacheStr = "";
        String firstStr = strs[0];
        if(strs.length==1){
            return strs[0];
        }
        for (int i = 0; i < firstStr.length(); i++) {
            String prefix = String.valueOf(firstStr.charAt(i));
            if (prefix.length() > 0) {
                prefix = cacheStr + prefix;
            }
            for (int y = 1; y < strs.length; y++) {
                if (strs[y].startsWith(prefix)) {
                    if (strs.length - 1 == y) {
                        cacheStr = prefix;
                    }
                } else {
                    return cacheStr;
                }
            }

        }
        return cacheStr;
    }

    public int romanToInt(String s) {
        s = s.replace("IV", "a");
        s = s.replace("IX", "b");
        s = s.replace("XL", "c");
        s = s.replace("XC", "d");
        s = s.replace("CD", "e");
        s = s.replace("CM", "f");

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += getValue(s.charAt(i));
        }
        return res;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            case 'a':
                return 4;
            case 'b':
                return 9;
            case 'c':
                return 40;
            case 'd':
                return 90;
            case 'e':
                return 400;
            case 'f':
                return 900;
        }
        return 0;
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
            if (currentChar == chars[chars.length - i - 1]) {
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
