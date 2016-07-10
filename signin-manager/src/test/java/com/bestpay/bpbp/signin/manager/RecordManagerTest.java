/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.base.BaseSpringTest;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Record;
import com.bestpay.bpbp.signin.dal.models.RecordRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


/**
 * 员工签到信息测试类
 * @author linxing
 */
@Slf4j
public class RecordManagerTest extends BaseSpringTest {
    @Autowired
    private RecordManager recordManager;

    /**
     * 查询员工签到信息测试
     */
    @Test
    public void findRecordTest() {
        try {
            RecordRequest rd =new RecordRequest();
            rd.setEmployeeName("测试");
            recordManager.selectRecordList(rd,new Pageable());
            log.info("测试:{}","测试成功");
        } catch (Exception e) {
            log.error("测试结果{}", e);
        }
    }

    /**
     * 插入员工签到信息测试
     */
    @Test
    public void insertRecordTest(){
        try {
            Record rd =new Record();
            rd.setStartTime(new Date());
            rd.setEmployeeId(2);
            int insertResult = recordManager.insertStartRecordInfo(rd);
            log.info("测试结果{}",insertResult);
        } catch (Exception e) {
            log.error("测试结果{}", e);
        }
    }

    /**
     * 更新员工签到信息测试
     */
    @Test
    public void updateRecordTest(){
        try {
            Record rd =new Record();
            rd.setEmployeeId(1);
            int updateResult = recordManager.updateRecordByEmployeeId(rd);
            log.info("测试结果{}",updateResult);
        } catch (Exception e) {
            log.error("测试结果{}", e);
        }
    }

    /**
     * 删除员工签到信息测试
     */
    @Test
    public void deleteRecordByIdTest(){
        try {
            Record rd =new Record();
            rd.setRecordId(3);
            recordManager.deleteRecordByEmployeeId(rd);
            log.info("测试结果{}","删除成功");
        } catch (Exception e) {
            log.error("测试结果{}",e);
        }
    }

}
