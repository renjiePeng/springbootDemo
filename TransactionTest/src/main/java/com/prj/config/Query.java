package com.prj.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * @Author prj
 *创建通用查询，根据当前页，页大小和排序条件进行创建分页
 */
public class Query<T>{

    public IPage<T> getPage(PageBean pageBean) {

        return new Page<>(pageBean.getPage(), pageBean.getSize());
    }
}
