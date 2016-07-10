package com.bestpay.bpbp.signin.manager.dto.request;

import com.bestpay.bpbp.signin.manager.dto.common.CommonRequest;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易域平台记录的请求
 * @author zhaoss
 * Version 1.0
 * Date 2016/5/16 11:32
 */
@Getter
@Setter
public class RecordInfoRequest extends CommonRequest implements Serializable{


    private static final long serialVersionUID = 8148131709657756145L;
    /**
     * 记录表主键
     */
    private int record;
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
     * 工时，以小时计算。
     */
    private double hoursWork;

    /**
     * 员工姓名：查询条件
     */
    private String employeeName;

    /**
     * 是否管理员
     */
    private  int isAdminFlag;

}
