package com.bestpay.bpbp.signin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 *
 * Author GarryChan
 * Version 1.0
 * Date 2015/10/17 14:42
 */
@Data
public class PageParameter implements Serializable {
    /**
     * 默认当前页数
     */
    public static final long DEFAULT_CURRENT_PAGE = 1;
    /**
     * 默认当前每页显示个数
     */
    public static final long DEFAULT_PAGE_SIZE    = 10;
    /**
     * 每页记录数
     */
    private long             pageSize;
    /**
     * 当前页数
     */
    private long             currentPage;
    /**
     * 总记录数
     */
    private long             totalCount;

    public PageParameter() {
        this.currentPage = DEFAULT_CURRENT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 初始化页面数据
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     */
    public PageParameter(long currentPage, long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
