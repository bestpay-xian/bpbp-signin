package com.bestpay.bpbp.signin.common.enums;

import lombok.Getter;

/**
 * 签到系统错误异常
 *
 * @author zhaoss
 *         Version 1.0
 *         Date 2015/10/17 14:34
 */
@Deprecated
public enum SigninErrorCodeEnum {

    SUCCESS("000000", "成功"),

    RES_PARAM_ERROR("010000", "参数错误"),

    REQUEST_NO_PERMISSION("011000", "没有相关权限"),

    //**查询数据为空***/
    QUERY_DATA_EMPTY("023999", "查询数据为空");
    @Getter
    private String errorcode;

    @Getter
    private String errordesc;

    /**
     * 构造函数
     *
     * @param errorCode 枚举key
     * @param errorDesc 枚举信息
     */
    SigninErrorCodeEnum(String errorCode, String errorDesc) {
        this.errorcode = errorCode;
        this.errordesc = errorDesc;
    }

    /**
     * 通过枚举key获取枚举信息
     *
     * @param errorCode 枚举key
     * @return 枚举信息
     */
    public static String getDesc(String errorCode) {
        for (SigninErrorCodeEnum bussErrorCode : SigninErrorCodeEnum.values()) {
            if (bussErrorCode.errorcode.equals(errorCode)) {
                return bussErrorCode.errordesc;
            }
        }
        return errorCode;
    }

    @Override
    public String toString() {
        return "SigninErrorCodeEnum[" + errorcode + "][" + errordesc + "]";
    }
}
