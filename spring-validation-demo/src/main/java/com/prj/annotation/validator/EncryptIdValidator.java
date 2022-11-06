package com.prj.annotation.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: com.prj.annotation.validator.EncryptIdValidator
 * @description: 自定义校验器的具体实现逻辑
 * @author: pengrj
 * @create: 2022-11-03 17:35
 */
public class EncryptIdValidator implements ConstraintValidator<EncryptId, String> {
    private static final Pattern PATTERN = Pattern.compile("^\\d+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 不为null才进行校验
        if (value != null) {
            Matcher matcher = PATTERN.matcher(value);
            return matcher.find();
        }
        return true;
    }
}

