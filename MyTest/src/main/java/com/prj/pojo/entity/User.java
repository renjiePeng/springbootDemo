package com.prj.pojo.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @className: com.prj.pojo.entity.User
 * @description:
 * @author: pengrj
 * @create: 2022-10-25 10:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @JsonProperty(value = "named")
    private String name;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
