package com.bestpay.bpbp.signin.dal.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Record
 * Author Liyang
 * Version 1.0
 * Date 2016/5/16 11:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Record implements Serializable{



    /**
     * 记录表主键
     */
    private int recordId;
    /**
     * 员工表主键
     */
    private int employeeId;
    /**
     * 签到开始时间(YYYY-mm-dd HH-MM:ss)
     */
    private Date startTime;
    /**
     * 签到结束时间(YYYY-mm-dd HH-MM:ss)
     */
    private Date endTime;
    /**
     * 工时,精确到分钟
     */
    private float hoursWork;

    /**
     * 员工姓名
     */
    private String employeeName;

}
