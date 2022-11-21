package com.prj.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: com.prj.pojo.entity.Student
 * @description:
 * @author: pengrj
 * @create: 2022-11-15 15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private String id;

    private String name;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private String birthday;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss.SSS",timezone ="GMT+8")
    private String createTime;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
