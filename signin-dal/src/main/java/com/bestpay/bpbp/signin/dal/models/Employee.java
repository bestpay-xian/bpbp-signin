package com.bestpay.bpbp.signin.dal.models;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Employee
 * Author Liyang
 * Version 1.0
 * Date 2016/5/16 11:31
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Serializable{

    /**
     * 员工主键
     */
    private int employeeId;

    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工电话
     */
    private String phone;
    /**
     * 员工qq
     */
    private int qq;
    /**
     * 员工email
     */
    private String email;
    /**
     * 员工密码
     */
    private String password;

    /**
     * 是否管理员
     */
    private int isAdmin;
    /**
     * 平台Id
     */
    private int platformId;
    /**
     * 组Id
     */
    private int teamId;

    /**
     * 是否删除(1-存在  2-删除)
     */
    private int isDelete;

}
