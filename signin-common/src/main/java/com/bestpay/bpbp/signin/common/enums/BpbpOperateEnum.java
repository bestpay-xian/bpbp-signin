/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.common.enums;

/**
 * 销账系统操作类型枚举
 * 
 * @author Weiliang
 * @version $Id: BpbpOperateEnum.java, v 0.1 2015年11月23日 下午3:12:18 Weiliang Exp $
 */
public enum BpbpOperateEnum {

    SYNC_ORDER("SYNC_ORDER", "订单同步")
    ;

    /** 枚举编码 */
    private String code;

    /** 描述说明 */
    private String description;

    /**
     * 根据<code>code</code>(枚举码)和<code>description</code>(枚举描述)构建一个枚举对象。
     * 
     * @param code          枚举码
     * @param description   枚举描述
     */
    BpbpOperateEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     * 
     * @param code  枚举编码
     * @return  返回编码对应的枚举类型
     */
    public static BpbpOperateEnum getByCode(String code) {
        for (BpbpOperateEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>description</tt>.
     * 
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

}
