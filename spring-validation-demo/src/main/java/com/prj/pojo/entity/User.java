package com.prj.pojo.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @className: com.prj.pojo.entity.User
 * @description:
 * @author: pengrj
 * @create: 2022-11-02 19:48
 */
public class User {

    @Length(min = 2)
    private String name;

    @Max(value = 100)
    private Integer age;

    @NotNull
    private String work;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "name:"+name+",age:"+age+",work:"+work;
    }
}
