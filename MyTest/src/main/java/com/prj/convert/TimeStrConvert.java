package com.prj.convert;

import com.prj.pojo.dto.UserDTO;
import com.prj.pojo.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * @className: com.prj.convert.TimeStrConvert
 * @description:
 * @author: pengrj
 * @create: 2022-11-12 16:07
 */
@Component
public class TimeStrConvert implements Converter<String, String> {
    Pattern notMillsPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}$");


    Pattern hasMillsPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}\\.\\d{3}");

    @Override
    public String convert(String source) {
        if(notMillsPattern.matcher(source).find()){
            LocalDateTime localDateTime = LocalDateTime.parse(source);
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }else if(hasMillsPattern.matcher(source).find()){
            LocalDateTime localDateTime = LocalDateTime.parse(source,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }else{
            return source;
        }
    }
}
