/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.service.interceptor;

import java.lang.reflect.Method;

import com.bestpay.bpbp.signin.manager.dto.common.CommonResponse;
import com.bestpay.bpbp.signin.service.log.BpbpSigninDigestLog;
import com.bestpay.bpbp.signin.service.log.BpbpSigninDigestLogHolder;
import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 销账核心摘要日志拦截器
 * 
 * @author Weiliang
 * @version $Id: BpbpSigninDigestLogInterceptor.java, v 0.1 2015年11月23日 上午10:59:05 Weiliang Exp $
 */

@Slf4j
@Service
public class BpbpSigninDigestLogInterceptor implements MethodInterceptor {

    /** 销账核心摘要日志 */
    private static final Logger digestLogger = LoggerFactory.getLogger("DIGEST-LOG");

    /** 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // 1、清空日志上下文存放器
        BpbpSigninDigestLogHolder.clear();

        CommonResponse result = null;
        long elapse = -1L;

        try {

            // 2、计算调用时长
            long startTime = System.currentTimeMillis();
            {
                // 3、执行被代理的方法
                result = (CommonResponse) invocation.proceed();
            }
            elapse = System.currentTimeMillis() - startTime;

            return result;

        } catch (Exception e) {
            log.error("处理异常:{}",e);
            throw e;
        } finally {

            if (result != null && BpbpSigninDigestLogHolder.get() != null) {
                try {

                    // 4、打印摘要日志
                    printDigestLog(invocation.getMethod(), elapse, result);

                } catch (Exception e) {
                    log.error("打印销账核心摘要日志时出现异常：", e);
                }
            }

            BpbpSigninDigestLogHolder.clear();

        }
    }

    /**
     * 打印摘要日志。
     * 
     * @param method    被代理的方法
     * @param elapse    调用时长
     * @param result    调用结果
     */
    @SuppressWarnings("rawtypes")
    private void printDigestLog(Method method, long elapse, CommonResponse result) {

        BpbpSigninDigestLog digestLog = BpbpSigninDigestLogHolder.get();

        // 补全摘要信息
        completeDigestLog(method, elapse, result, digestLog);

        if (digestLogger.isInfoEnabled()) {
            digestLogger.info(digestLog.toDigestLog());
        }

    }

    /**
     * 补全摘要日志。
     * 
     * @param method    被代理的方法
     * @param elapse    调用时长
     * @param result    调用结果
     * @param digestLog 日志内容
     */
    @SuppressWarnings("rawtypes")
    private void completeDigestLog(Method method, long elapse, CommonResponse result,
                                   BpbpSigninDigestLog digestLog) {
        String invocationSignature = method.getDeclaringClass().getSimpleName() + "."
                                     + method.getName();
        digestLog.setElapse(elapse);
        digestLog.setInvocationMethod(invocationSignature);
        digestLog.setRespCode(result.getRespCode());
        digestLog.setRespDesc(result.getRespDesc());
    }

}
