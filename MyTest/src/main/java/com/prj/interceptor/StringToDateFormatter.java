package com.prj.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @className: com.prj.interceptor.StringToDateFormatter
 * @description:
 * @author: pengrj
 * @create: 2022-10-26 13:35
 */
//@Component
public class StringToDateFormatter implements Formatter<Date> {

    private String datePattern = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        System.out.println("from StringToDateFormatter ");
        long milli = LocalDateTime.parse(text, dateTimeFormatter).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        return new Date(milli);
    }

    @Override
    public String print(Date date, Locale locale) {
        long time = date.getTime();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("Asia/Shanghai")).format(dateTimeFormatter);
    }
}

