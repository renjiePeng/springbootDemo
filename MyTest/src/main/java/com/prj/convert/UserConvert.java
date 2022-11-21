package com.prj.convert;

import com.prj.pojo.dto.UserDTO;
import com.prj.pojo.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @className: com.prj.convert.UserConvert
 * @description:
 * @author: pengrj
 * @create: 2022-11-10 10:41
 */
@Component
public class UserConvert implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.parseInt(source);
    }

//    @Override
//    public User convert(String source) {
//        System.out.println("String source类型转为 User");
//        String[] split = source.split(",");
//        Assert.isTrue(split.length==3,"转换失败！");
//        return User.builder().name(split[0]).age(Integer.parseInt(split[1])).date(new Date()).build();
//    }
}
