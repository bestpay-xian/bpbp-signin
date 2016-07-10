package com.bestpay.bpbp.signin.common.utils;

import java.util.Date;

/**
 * IdUtil
 * Author Liyang
 * Version 1.0
 * Date 2016/5/23 8:58
 */
public class StringUtil {

    public static long getNo(){
        return new Date().getTime();
    }

    public static void main(String[] args) {
        System.out.println("args = " + DateUtil.getCurrent("yyyyMMddHHmmssSS"));
    }
}
