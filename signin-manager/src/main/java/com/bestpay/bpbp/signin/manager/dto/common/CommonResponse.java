package com.bestpay.bpbp.signin.manager.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 应答数据传输公共实体类
 *@author zhaoss
 * Date 2015/6/30 17:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 2711051368805419334L;

    /***
     * 响应码
     */
    private String respCode;

    /***
     * 响应描述
     */
    private String respDesc;

    /**
     * 应答实体
     */
    private T result;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
