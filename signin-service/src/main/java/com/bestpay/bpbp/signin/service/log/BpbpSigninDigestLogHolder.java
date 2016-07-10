/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.service.log;

import com.bestpay.bpbp.signin.common.enums.BpbpOperateEnum;

/**
 * 销账核心摘要日志存放器
 * 
 * @author Weiliang
 * @version $Id: BpbpSiginDigestLogHolder.java, v 0.1 2015年11月23日 下午2:37:33 Weiliang Exp $
 */
public class BpbpSigninDigestLogHolder {

    /** 销账核心摘要日志本地线程变量  */
    private static ThreadLocal<BpbpSigninDigestLog> digestLogger = new ThreadLocal<BpbpSigninDigestLog>();

//    /**
//     * 初始化摘要日志，提取出摘要日志在本容器中。
//     *
//     * @param id id
//     * @param operateType 枚举信息
//     */
//    public static void init(String id, BpbpOperateEnum operateType) {
//        BpbpSigninDigestLog digestLog = new BpbpSigninDigestLog();
//        digestLog.setId(id);
//        digestLogger.set(digestLog);
//    }
    /**
     * 初始化摘要日志，提取出摘要日志在本容器中。
     *
     * @param id id
     */
    public static void init(String id) {
        BpbpSigninDigestLog digestLog = new BpbpSigninDigestLog();
        digestLog.setId(id);
        digestLogger.set(digestLog);
    }

    /**
     * 获取摘要日志。
     *
     * @return  摘要日志对象
     */
    public static BpbpSigninDigestLog get() {
        return digestLogger.get();
    }

    /**
     * 清理本地线程变量中的摘要日志。
     */
    public static void clear() {
        digestLogger.set(null);
    }

}
