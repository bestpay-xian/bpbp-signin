package com.bestpay.bpbp.signin.dal.models;

import lombok.*;

/**
 * Dept
 * Author Liyang
 * Version 1.0
 * Date 2016/5/23 10:53
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dept {

    /**
     * 部分Id
     */
    private int deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 是否删除 1-存在 2-删除
     */
    private int isDelete;

}
