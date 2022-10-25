package com.prj.pojo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className: com.prj.pojo.entity.User
 * @description:
 * @author: pengrj
 * @create: 2022-10-25 10:12
 */
@Data
public class User {
    private String name;

    private Integer age;

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(12);
        user1.setName("张三");
        User user2 = new User();
        user2.setAge(17);
        user2.setName("李四");
        users.add(user1);
        users.add(user2);

        Map<String, Integer> map = users.stream().collect(
                Collectors.toMap(
                        User::getName, User::getAge,(oldValue,newValue)->newValue, LinkedHashMap::new
                )
        );
//        Map<String, Integer> map = users.stream().collect(Collectors.toMap(User::getName, User::getAge,null, LinkedHashMap::new));
        System.out.println(map);

    }
}
