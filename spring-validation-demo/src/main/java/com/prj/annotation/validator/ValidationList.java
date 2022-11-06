package com.prj.annotation.validator;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: com.prj.annotation.validator.ValidationList
 * @description: 用于验证批量插入的实体对象
 * @author: pengrj
 * @create: 2022-11-03 17:00
 */
@Data
public class ValidationList<E> implements List<E> {

    @Delegate
    @Valid
    public List<E> list = new ArrayList<>();

    // 一定要记得重写toString方法
    @Override
    public String toString() {
        return list.toString();
    }
}
