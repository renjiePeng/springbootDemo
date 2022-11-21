package com.prj.convert;

import com.prj.pojo.dto.UserDTO;
import com.prj.pojo.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * @className: com.prj.convert.UserConvert
 * @description:
 * @author: pengrj
 * @create: 2022-11-10 10:41
 */
@Component
public class UserDTOConvert implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO userDTO) {
        System.out.println("UserDTO userDTO类型转为 User");
        User target = new User();
        BeanUtils.copyProperties(userDTO, target);
        return target;
    }
}
