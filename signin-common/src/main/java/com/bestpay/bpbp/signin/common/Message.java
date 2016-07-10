package com.bestpay.bpbp.signin.common;

import java.io.Serializable;

/**
 * Message
 * Author Liyang
 * Version 1.0
 * Date 2016/5/18 10:30
 */
public class Message implements Serializable{

    /**
     * 类型
     */
    public enum Type {

        /** 成功 */
        success,

        /** 警告 */
        warn,

        /** 错误 */
        error
    }

    public Type type;
    /**
     * 返回结果集
     * 如果是列表 return list<Object>
     * 如果是单表 return Object
     */
    public Object result;

    /**
     * 提示信息
     * 如果成功 -提示成功信息
     * 如果失败 -提示失败信息
     */
    public String info;

    /**
     *
     * @param type
     * @param result
     * @param info
     */
    public Message(Type type, Object result, String info){
        this.type = type;
        this.result = result;
        this.info = info;
    }
    /**
     *
     * @param type
     * @param info
     */
    public Message(Type type, String info){
        this.info = info;
        this.type = type;
    }

    /**
     * 返回成功的信息
     * @param result 结果集
     * @param info   提示成功信息
     * @return 信息
     */
    public static Message successRst(Object result,String info) {
        return new Message(Type.success, result, info);
    }
    /**
     * 返回成功的信息
     * @param info   提示成功信息
     * @return 信息
     */
    public static Message successRst(String info) {
        return new Message(Type.success, info);
    }

    /**
     * 返回失败的信息
     * @param result 结果集
     * @param info   提示失败信息
     * @return 信息
     */
    public static Message errorRst(Object result,String info) {
        return new Message(Type.error, result, info);
    }
    /**
     * 返回失败的信息
     * @param info   提示失败信息
     * @return 信息
     */
    public static Message errorRst(String info) {
        return new Message(Type.error, info);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
