package com.prj;

import lombok.Data;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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


    /**
     * 考察点：单向链表
     * 链表是一种物理存储单元上非连续、非顺序的存储结构，数据元素的逻辑顺序是通过链表中的指针链接次序实现的。
     * 链表由一系列节点组成，节点可以在运行时动态生成，节点包括两个部分：一个是存储数据元素的数据域，
     * 另一个是存储下一个结点地址的指针域。
     */
    @Test
    public void test6(){
        //StudentNode headNode = new StudentNode(0, "");
        ListNode listNode1 = new ListNode();
        ListNode listNode2 = new ListNode();
        ListNode listNode3 = new ListNode();
        listNode2.setVal(5);
        listNode3.setVal(3);
        listNode1.setVal(2);
        listNode2.setNext(listNode3);
        listNode1.setNext(listNode2);


        ListNode listNode11 = new ListNode();
        ListNode listNode12 = new ListNode();
        ListNode listNode13 = new ListNode();
        listNode12.setVal(7);
        listNode13.setVal(9);
        listNode11.setVal(0);
        listNode12.setNext(listNode13);
        listNode11.setNext(listNode12);

        ListNode listNode = sortListNode1(listNode1, listNode11);
        System.out.println(listNode);
    }

    public int removeDuplicates1(@NotNull int[] nums) {
        if(nums.length==1){
            return nums.length;
        }

        int i = 0;
        int j = 1;
        while(j<nums.length){
            if(nums[i] != nums[j]){
                i = i +1;
                nums[i] = nums[j];
            }
            j++;
        }
        return i+1;
    }

    /**
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     *
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test8(){
        int[] nums = {7,8,8,10,12,13,14,16,16};
        System.out.println(removeDuplicates1(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
                StringBuilder builder = new StringBuilder();
                for (int num : nums) {
                    builder.append(num + ",");
                }
                System.out.println(builder.toString());
            }
            j++;
        }
        return ++i;
    }

//    public int removeDuplicates(int[] nums) {
//        if(nums.length==1){
//            return nums.length;
//        }
//
//        int lastIndex = 0;
//        int currentIndex = 1;
//        while (lastIndex<=nums.length-1 && currentIndex<= nums.length-1){
//            if(nums[lastIndex] == nums[currentIndex]){
//                currentIndex++;
//            }else{
//                lastIndex++;
//                System.out.println("前：nums[lastIndex]:"+nums[lastIndex]+ " nums[currentIndex]:"+nums[currentIndex]);
//                nums[lastIndex]=nums[currentIndex];
//                System.out.println("后：nums[lastIndex]:"+nums[lastIndex]+ " nums[currentIndex]:"+nums[currentIndex]);
//                StringBuilder builder = new StringBuilder();
//                for (int num : nums) {
//                    builder.append(num+",");
//                }
//                System.out.println(builder.toString());
//                currentIndex++;
//            }
//        }
//        return lastIndex+1;
//    }

    private ListNode sortListNode(ListNode listNode1, ListNode listNode2){
        if(Objects.isNull(listNode1)){
            return listNode2;
        }

        if(Objects.isNull(listNode2)){
            return listNode1;
        }

        ListNode tmpListNode = new ListNode(-1);
        while(Objects.nonNull(listNode1) && Objects.nonNull(listNode2)){
            if(listNode1.getVal() > listNode2.getVal()){
                tmpListNode.setNext(listNode2);
                listNode2 = listNode2.next;
            }else if(listNode1.getVal() < listNode2.getVal()){
                tmpListNode.setNext(listNode1);
                listNode1 = listNode1.next;
            }
        }
        return tmpListNode;
    }

    private ListNode sortListNode1(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode node = new ListNode(-1);  //定义新节点用于形成新链表
        ListNode temp = node;   //用来遍历
        while(list1 != null && list2 != null){
            if(list2.val >= list1.val){   //标胶两条链表的初始节点；哪个节点小就拼接到node上，并且该链表后移一位
                temp.next = list1;
                list1 = list1.next;
            }else if(list2.val < list1.val){
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;   //node后移一位，指向尾结点
            if(list1 == null){  //当某条链表结束了，就让node.next指向另一条链表的空余，因为是升序的嘛
                temp.next = list2;
                return node.next;
            }
            if(list2 == null){
                temp.next = list1;
                return node.next;   //返回初始节点
            }
        }
        return node.next;
    }

    @Test
    public void test7(){
        long currentTimeMillis = System.currentTimeMillis();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeMillis), ZoneOffset.UTC).format(dateTimeFormatter);

        System.out.println("ZoneOffset.UTC:       "+format1);

        String format2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeMillis), ZoneId.of("Asia/Shanghai")).format(dateTimeFormatter);

        System.out.println("ZoneId.systemDefault(): "+format2);

        String format3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeMillis), ZoneOffset.of("+8")).format(dateTimeFormatter);

        System.out.println("ZoneOffset.of(\"+8\"):     "+format3);
    }


    public void addNodeByIdAsc(StudentNode headNode, StudentNode newStudentNode){
        StudentNode tmpNode = headNode;
        while(true){
            if(Objects.isNull(tmpNode.getNextStudent())){
                tmpNode.setNextStudent(newStudentNode);
                break;
            }

            int newStudentNodeId = newStudentNode.getId();
            if(tmpNode.getNextStudent().getId() == newStudentNodeId){
                break;
            }

            if(tmpNode.getNextStudent().getId()> newStudentNodeId){
                newStudentNode.setNextStudent(tmpNode.getNextStudent());
                tmpNode.setNextStudent(newStudentNode);
                break;
            }
            //tmpNode 后移一位
            tmpNode = tmpNode.getNextStudent();
        }
    }

    public void addNodeEnd(StudentNode newStudentNode){
        StudentNode tmpNode = newStudentNode;
        while (true){
            if(Objects.isNull(tmpNode.getNextStudent())){
                break;
            }
            //将tmpNode后移
            tmpNode = tmpNode.getNextStudent();
        }
        //当退出while循环后，tmpNode就指向了链表的最后
        //将最后这个节点指向新插入的这个节点
        tmpNode.setNextStudent(newStudentNode);
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

@Data
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


@Data
class StudentNode {
    private int id;  // 学生id
    private String name;  // 学生姓名
    private StudentNode nextStudent;  // 指向下一个学生

    public StudentNode() {
    }

    public StudentNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
}