/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.service.log;


import com.bestpay.bpbp.signin.common.enums.BpbpOperateEnum;

import java.io.Serializable;

/**
 * 销账核心摘要日志
 * 
 * @author Weiliang
 * @version $Id: BpbpCoreDigestLog.java, v 0.1 2015年11月23日 上午11:41:52 Weiliang Exp $
 */
public class BpbpSigninDigestLog implements Serializable {

    /** UID */
    private static final long serialVersionUID = 3826255752416304373L;

    /** 响应码 */
    private String respCode;

    /** 响应描述 */
    private String respDesc;

    /** 单据ID */
    private String id;

    /** 操作类型 */
//    private BpbpOperateEnum operateType;

    /** 被调用方法 */
    private String invocationMethod;

    /** 操作耗时 */
    private long elapse;

    /**
    * 转换为摘要日志字符串。
    * 
    * @return  摘要日志字符串
    */
    public String toDigestLog() {

        StringBuilder buffer = new StringBuilder();

//        buffer.append("[");
//        {
//            buffer.append(StringUtils.defaultIfBlank(invocationMethod, "-")).append("] [");
//            buffer.append(StringUtils.defaultIfBlank(id, "-")).append("] [");
//            buffer.append(StringUtils.defaultIfBlank(respCode, "-")).append("] [");
//            buffer.append(StringUtils.defaultIfBlank(respDesc, "-")).append("] [");
//            buffer.append(Long.toString(Thread.currentThread().getId())).append("] [");
//            buffer.append(StringUtils.defaultIfBlank(String.valueOf(elapse), "-") + "ms");
//        }
        buffer.append("]");

        return buffer.toString();
    }

    /**
     * Getter method for property <tt>respCode</tt>.
     * 
     * @return property value of respCode
     */
    public String getRespCode() {
        return respCode;
    }

    /**
     * Setter method for property <tt>respCode</tt>.
     * 
     * @param respCode value to be assigned to property respCode
     */
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    /**
     * Getter method for property <tt>respDesc</tt>.
     * 
     * @return property value of respDesc
     */
    public String getRespDesc() {
        return respDesc;
    }

    /**
     * Setter method for property <tt>respDesc</tt>.
     * 
     * @param respDesc value to be assigned to property respDesc
     */
    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>invocationMethod</tt>.
     * 
     * @return property value of invocationMethod
     */
    public String getInvocationMethod() {
        return invocationMethod;
    }

    /**
     * Setter method for property <tt>invocationMethod</tt>.
     * 
     * @param invocationMethod value to be assigned to property invocationMethod
     */
    public void setInvocationMethod(String invocationMethod) {
        this.invocationMethod = invocationMethod;
    }

    /**
     * Getter method for property <tt>elapse</tt>.
     * 
     * @return property value of elapse
     */
    public long getElapse() {
        return elapse;
    }

    /**
     * Setter method for property <tt>elapse</tt>.
     * 
     * @param elapse value to be assigned to property elapse
     */
    public void setElapse(long elapse) {
        this.elapse = elapse;
    }

}
