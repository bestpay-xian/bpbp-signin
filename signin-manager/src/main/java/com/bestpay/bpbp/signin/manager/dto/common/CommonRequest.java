package com.bestpay.bpbp.signin.manager.dto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求数据传输公共实体类
 *@author  zhaoss
 * Date 2015/6/30 16:46
 */
@Data
public class CommonRequest implements Serializable {

    private static final long serialVersionUID = 4915939527387366737L;
    /**
     * 每页条数
     */
    private int               pageSize         = 10;

    /**
     * 当前页数
     */
    private int currentPage = 1;

}
