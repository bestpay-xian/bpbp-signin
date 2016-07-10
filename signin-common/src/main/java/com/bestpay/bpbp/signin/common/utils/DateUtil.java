/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * 时间工具类
 *
 * @author linxing
 * @version Id: DateUtil.java, v 0.1 2016/5/23 9:52 linxing Exp $$
 */
public class DateUtil {
    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();
    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    /**
     * 日期时间格式 *
     */
    public static final String fullPattern = "yyyyMMddHHmmss";
    public static final String settlePattern = "yyyy-MM-dd HH:mm:ss";
    public static final String datePattern = "yyyyMMdd";
    public static final String shotPattern = "yyyy-MM-dd";
    public static final String timePattern = "HHmmss";
    public static final String timesPattern = "yyyy/MM/ddHH:mm:ss";
    public static final String shortDatePattern = "yyMMdd";
    public static final String fullPatterns = "yyyyMMddHHmmssSS";
    public static final String partPattern = "yyMMddHHmmss";
    public static final String ticketPattern = "yyyy.MM.dd HH:mm:ss";
    public static final String hour_of_minute = "HHmm";
    public static final String timeColPattern = "HH:mm:ss";
    public static final String dateFullPattern = "yyyyMMdd HH:mm:ss";
    public static final String year_of_minute = "yyyyMMddHHmm";
    public static final String yearDate = "yyyy-MM-dd HH:mm";

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String getCurrent() {
        return format(new Date(), DateUtil.settlePattern);
    }

    /**
     * 按指定的格式获取当前时间
     *
     * @return 当前时间
     */
    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    /**
     * 使用线程容器来获取SimpleDateFormat
     *
     * @param date    日期
     * @param pattern 格式
     * @return 指定格式的日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return getSdf(pattern).format(date);
    }

    /**
     * 将日期转成指定的字符串格式
     *
     * @param date    日期
     * @param pattern 格式
     * @return 指定格式的日期字符串
     */
    public static String format2(Date date, String pattern) {
        if (date == null)
            return null;
        return new DateTime(date).toString(pattern);
    }

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern 时间格式
     * @return 指定格式的时间
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * 日期转换
     *
     * @param dateStr 字符串的日期
     * @param pattern 格式
     * @return 转换后的日期
     * @throws java.text.ParseException
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

    /**
     * 将字符串转为指定格式的日期
     *
     * @param date   字符串的日期
     * @param format 格式
     * @return 转换后的日期
     * @throws java.text.ParseException
     */
    public static Date formatString(String date, String format) {
        return DateTime.parse(date, DateTimeFormat.forPattern(format)).toDate();
    }

    /**
     * 将字符串转为指定格式的日期
     *
     * @param date    字符串的日期
     * @param pattern 格式
     * @return 转换后的日期
     * @throws java.text.ParseException
     */
    public static DateTime parseTime(String date, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(date);
    }

    /**
     * 通过开始时间，经过的天数计算到达的日期
     *
     * @param startDate
     * @param days
     * @return
     */
    public static String calEndDate(Date startDate, int days) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);
        c1.set(Calendar.DATE, c1.get(Calendar.DATE) + days);
        Date endDate = c1.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(shotPattern);
        String arriveDate = sdf.format(endDate);
        return arriveDate;
    }

    /**
     * 通过订单号获取订单的生存日期
     *
     * @param bpbpOrderNo
     * @return
     */
    public static String getOrderCreateDate(String bpbpOrderNo) throws ParseException {
        /**销账表需要分区，bpbpOrderNo的生存规则yyyymmdd+seq  需要截取前面的日期*/
        String orderCreateDate = bpbpOrderNo.substring(0, 8);
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        Date date = sdf.parse(orderCreateDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat(shotPattern);
        String createDate = sdf1.format(date);
        return createDate;
    }

    /**
     * 将字符传格式时间转换为日期
     *
     * @param createDate
     * @return
     */
    public static Date createDateConverter(String createDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat(shotPattern);
        Date orderCreateDate = sdf1.parse(createDate);
        return orderCreateDate;
    }

    /**
     * 计算前几分钟的时间
     *
     * @param minute 分钟数
     * @return 时间
     */
    public static String getCurrentAfter(int minute) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(new Date());
        targetTime.add(Calendar.MINUTE, minute);
        return format(targetTime.getTime(), DateUtil.fullPattern);
    }

    /**
     * 时间格式转换
     *
     * @param date          时间字符串
     * @param originPattern 原时间格式
     * @param targetPattern 新的时间格式
     * @return 转换后的日期字符串
     * @throws java.text.ParseException
     */
    public static String convert(String date, String originPattern,
                                 String targetPattern) throws ParseException {
        Date originDate = parse(date, originPattern);
        return format(originDate, targetPattern);
    }

    /**
     * 源日期和（目标日期加上毫秒数）比较大小， 大则返回false ，小返回true
     *
     * @param src    源日期
     * @param target 目的日期
     * @param second 秒数
     * @return 成功，失败
     */
    public static boolean compareDateForSecond(Date src, Date target, int second) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(target);
        targetTime.add(Calendar.SECOND, second);
        Calendar srcTime = Calendar.getInstance();
        srcTime.setTime(src);
        return srcTime.compareTo(targetTime) <= 0;
    }

    /**
     * 获取指定格式的当前时间
     *
     * @param pattern
     * @return
     */
    public static Date parseTime(String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(getCurrent(pattern)).toDate();
    }

    /**
     * 获取当前指定日志之后的第几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date plusDate(Date date, int day) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(day).toDate();
    }

    /**
     * 将日志转为指定格式的字符串
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, fullPattern);
    }

    /**
     * 获取当前时间，日期格式
     *
     * @return
     */
    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前指定日志之后的第几天
     *
     * @param date
     * @param day
     * @param pattern
     * @return
     */
    public static String plusDateString(Date date, int day, String pattern) {
        return format2(plusDate(date, day), pattern);
    }

    /**
     * 计算两个日期之间的天数，包括开始和结束日期在内
     *
     * @param startDate 开始日期
     * @param endDate   结算日期
     * @return 天数
     */
    public static int betweenTwoDatesDays(String startDate, String endDate) {
        long start = parseTime(startDate, shotPattern).toDate().getTime();
        long end = parseTime(endDate, shotPattern).toDate().getTime();
        if (start == end) {
            return 1;
        } else {
            int days = ((int) (end - start) / (1000 * 60 * 60 * 24)) + 1;
            return days;
        }
    }

    /**
     * 计算同一天中两个时间点之间的小时，精确到分钟
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 间隔小时数
     */
    public static float timeDifference(Date startDate, Date endDate) {
//        long diff = endDate.getTime() - startDate.getTime();
//        long hour = (diff / (60 * 60 * 1000));
//        long min = ((diff / (60 * 1000)) - hour * 60);
//        DecimalFormat df = new DecimalFormat("0.0");
//
//        double totalDate = hour + Double.parseDouble(df.format((double) min / 60));
//
//        return  totalDate;

        long l = endDate.getTime() - startDate.getTime();
        if (l == 0) {
            return 0;
        }
        float hour = (l / (60 * 60 * 1000));
        float min = ((l / (60 * 1000)) - hour * 60) / 60;

        float totalDate = (float) (Math.round((hour + min) * 10)) / 10;

        return totalDate;

    }

    /**
     * 获取当前的小时
     *
     * @return
     */
    public static int getHourFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if(null == date){
            calendar.setTime(DateUtil.getCurrentDate());
            return calendar.get(Calendar.HOUR_OF_DAY);
        }else{
            calendar.setTime(date);
            return calendar.get(Calendar.HOUR_OF_DAY);
        }
    }

}
