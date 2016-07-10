/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.dal.mapper;

import com.bestpay.bpbp.signin.dal.models.Record;
import com.bestpay.bpbp.signin.dal.models.RecordRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *员工签到信息处理类
 * author:linxing
 *
 */
public interface RecordMapper {

    /**
     * 查询签到记录信息
     * @return
     * @throws SQLException
     */
    List<Record> selectRecord(Map map) throws SQLException;

    /**
     * 查询签到总记录数
     * @return
     * @throws SQLException
     */
    Long selectRecordCount(RecordRequest record) throws  SQLException;

    /**
     * 员工签到
     * @param record
     * @return
     */
    int insertStartRecordInfo(Record record) throws SQLException;



    Record selectRecordByEmployeeIdToday(Record record) throws SQLException;

    /**
     * 员工退签
     * @param record
     * @return
     */
    int updateEndRecordInfo(Record record) throws SQLException;

    /**
     * 删除用户签到信息
     * @param record
     * @return
     */
    void deleteRecordByEmployeeId(Record record) throws SQLException;
}