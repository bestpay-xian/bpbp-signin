package com.bestpay.bpbp.signin.dal.models;

import lombok.*;

import java.io.Serializable;

/**
 * Center
 * Author 赵星
 * Version 1.0
 * Date 2016年5月23日10:29:02
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Center implements Serializable{

    /**
     * 中心表主键
     */
    private int centerId;
    /**
     * 中心名称
     */
    private String centerName;

    /**
     * 中心所属部门id
     */
    private int deptId;
    /**
     * 中心所属部门name
     */
    private String deptName;

    /**
     * 是否删除
     */
    private int isDelete;

}
