package com.bestpay.bpbp.signin.dal.models;

import lombok.*;

/**
 * Platform
 * Author Liyang
 * Version 1.0
 * Date 2016/5/16 11:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Platform {

    /**
     * 平台表主键
     */
    private int platformId;
    /**
     * 平台名称
     */
    private String name;
    /**
     * 组外键
     */
    private int group;
}
