package com.bestpay.bpbp.signin.dal.models;

import lombok.*;

import java.io.Serializable;

/**
 * Center
 * Author linxing
 * Version 1.0
 * Date 2016年5月23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team implements Serializable{

    /**
     * 小组表主键
     */
    private int teamId;

    /**
     * 小组名称
     */
    private String teamName;

    /**
     * 小组所属中心Id
     */
    private int centerId;

    /**
     * 小组所属中心名称
     */
    private String centerName;

    /**
     * 删除标记
     */
    private int deleteFlag;

}
