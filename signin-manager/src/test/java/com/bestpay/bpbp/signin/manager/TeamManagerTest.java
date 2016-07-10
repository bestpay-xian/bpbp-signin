/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.base.BaseSpringTest;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.mapper.TeamMapper;
import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.dal.models.Record;
import com.bestpay.bpbp.signin.dal.models.RecordRequest;
import com.bestpay.bpbp.signin.dal.models.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


/**
 * 小组信息测试类
 * @author linxing
 */
@Slf4j
public class TeamManagerTest extends BaseSpringTest {
    @Autowired
    private TeamManager teamManager;

    /**
     * 查询员工签到信息测试
     */
    @Test
    public void findTeamAllListTest() {
        try {
            Page<List<Team>> list =  teamManager.selectAllTeamInfo(new Team(), new Pageable());
            log.info("测试:{}",list);
        } catch (Exception e) {
            log.error("测试结果{}", e);
        }
    }

    /**
     * 插入小组信息测试
     */
    @Test
    public  void insertTeamInfoTest(){
        try {
            Team team = new Team();
            team.setCenterId(3);
            team.setTeamName("UT_NAME");
            int insertRes = teamManager.insertTeamInfo(team);
            log.info("测试:{}",insertRes);
        } catch (Exception e) {
            log.error("测试结果{}", e);
        }
    }

    /**
     * 更新小组信息测试
     */
    @Test
    public  void updateTeamInfoTest(){
        try {
            Team team = new Team();
            team.setTeamId(3);
            team.setCenterId(3);
            team.setTeamName("UT_U_NAME");
            int updateRes = teamManager.updateTeamInfoByPrimaryKey(team);
            log.info("测试:{}",updateRes);
        } catch (Exception e) {
            log.error("测试结果{}", e);
        }
    }

    /**
     * 删除小组信息测试
     */
    @Test
    public void deleteTeamInfoTest(){
        try {
            Team team = new Team();
            team.setTeamId(3);
            teamManager.deleteTeamInfoByPrimaryKey(team);
            log.info("测试:{}","删除成功");
        } catch (Exception e) {
            log.error("测试结果{}",e);
        }
    }

}
