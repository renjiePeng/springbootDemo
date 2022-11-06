package com.prj.pojo.dto;

import com.prj.annotation.validator.Add;
import com.prj.annotation.validator.EncryptId;
import com.prj.annotation.validator.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @className: com.prj.pojo.entity.User
 * @description:
 * @author: pengrj
 * @create: 2022-11-02 19:48
 */
public class UserDTO {

    @Length(min = 2,groups = Update.class)
    private String name;

    @Max(value = 100,groups = Update.class)
    private Integer age;

    @NotNull
    @Length(min = 1,groups = {Update.class, Add.class})
    private String work;

    @EncryptId(groups = {Update.class,Add.class})
    private String cardCode;

    @NotNull(groups = {Update.class,Add.class})
    private Date date;

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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "name:"+name+",age:"+age+",work:"+work+",cardCode:"+cardCode+",date:"+date.getTime();
    }
}
