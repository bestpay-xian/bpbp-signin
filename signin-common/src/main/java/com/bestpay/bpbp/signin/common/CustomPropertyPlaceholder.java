/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 配置信息加载类
 * @author weizhansong
 * @version Id: CustomPropertyPlaceholder.java, v 0.1 2015/12/22 17:58 weizhansong Exp $$
 */
@Slf4j
public class CustomPropertyPlaceholder extends PropertyPlaceholderConfigurer {

    /**
     * 缓存配置信息
     */
    private static Map<String, String> contextMap = new HashMap<String, String>();

    /**
     * 获取配置参数
     *
     * @param key key信息
     * @return String
     */
    public static String getStrPro(String key) {
        return contextMap.get(key);
    }

    /**
     * 获取整型配置参数值
     *
     * @param key key信息
     * @return int
     */
    public static int getIntPro(String key) {
        try {
            String val = contextMap.get(key);
            return Integer.parseInt(val);
        } catch (Exception e) {
            log.error("Get prop value of [{}] failed,because of the reason:{}.", key,
                e.getMessage());
        }
        return 0;
    }

    /**
     * 加载spring配置中的 properties 文件
     *
     * @param beanFactoryToProcess 工厂
     * @param props 配置文件
     * @throws BeansException
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        //        log.info("Init properties configure:...");

        StringBuilder sb = new StringBuilder();
        String key = null;
        String value = null;
        for (Object k : props.keySet()) {
            key = (String) k;
            value = (String) props.get(key);
            sb.append("[ ").append(key).append("\t:\t").append(value).append(" ]\r\n");
            contextMap.put(key, value);
        }
        log.info("Initialization properties configure finished.");
    }
}
