package com.prj.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author prj
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<T> records;
    private int size;
    private int current;
    private List<T> orders;
    private boolean optimizeCountSql;
    private boolean hitCount;
    private String countId;
    private Long maxLimit;
    private Boolean searchCount;
    private long pages;
    public PageResult() {
    }
//    public PageResult(Long total, List<T> records) {
//        this.total = total;
//        this.records = records;
//    }

    public PageResult(Long total, List<T> records, PageBean pageBean) {
        this.total = total;
        this.records = records;
        this.size = pageBean.getSize();
        this.current = pageBean.getPage();
        this.orders = new ArrayList<>();
        this.optimizeCountSql = true;
        this.hitCount=false;
        this.countId = null;
        this.maxLimit = null;
        this.searchCount=true;
        this.pages = 1;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<T> getOrders() {
        return orders;
    }

    public void setOrders(List<T> orders) {
        this.orders = orders;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public boolean isHitCount() {
        return hitCount;
    }

    public void setHitCount(boolean hitCount) {
        this.hitCount = hitCount;
    }

    public String getCountId() {
        return countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }

    public Long getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Long maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
