package com.bestpay.bpbp.signin.manager.dto.response;

import com.bestpay.bpbp.signin.manager.dto.common.CommonResponse;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易域平台记录的响应信息
 * @author zhaoss
 * Version 1.0
 * Date 2016/5/16 11:32
 */
@Data
public class RecordResponse  extends CommonResponse implements Serializable{


    private static final long serialVersionUID = 1724836354828747329L;
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
    /**
     * 总数用于分页
     */
    private Long              pageCount;
}
