package com.prj.config;

import java.io.Serializable;

/**
 * @Author : prj
 * @create 2022/4/25 16:51
 */
public class PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer page = 1;
    private Integer size = 10;

    public PageBean() {

    }

    public PageBean(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}