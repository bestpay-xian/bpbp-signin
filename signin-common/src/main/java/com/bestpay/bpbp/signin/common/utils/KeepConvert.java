/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.common.utils;

import java.util.UUID;

import org.slf4j.MDC;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import com.bestpay.bpbp.signin.common.constant.Constant;

/**
 * 将客户端送的keep打印在日志中，如果获取不到则获取一个UUID
 *
 * @author gaoxiang
 * @version Id: KeepConvert.java, v 0.1 2016/1/18 15:29 gaoxiang Exp $$
 */
public class KeepConvert extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        String keep = MDC.get(Constant.TRACE_LOG_ID);
        if (keep != null) {
            return keep;
        }
        return UUID.randomUUID().toString();
    }
}
