package com.bestpay.bpbp.signin.common;

/**
 * 分页帮助类
 * Author: liliangxing
 * Time: 2015-07-15.
 */
public class LaunchServiceUtils {

    /**
     * 生成分页参数对象
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return PageParameter
     */
    public static PageParameter getPageParameter(long currentPage, long pageSize) {
        PageParameter page = new PageParameter();
        if (currentPage != 0) {
            page.setCurrentPage(currentPage);
        }
        if (pageSize != 0) {
            page.setPageSize(pageSize);
        }
        return page;
    }
}
