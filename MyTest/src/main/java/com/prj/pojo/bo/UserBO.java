package com.prj.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
public class UserBO {

    private String time;

    private String version;

    private String name;

    private Integer agE;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
