/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.base.BaseSpringTest;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Platform;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * PlatformManagerTest
 * Author 赵星
 * Version 1.0
 * Date 2016年5月20日10:20:10
 */
@Slf4j
public class PlatformManagerTest extends BaseSpringTest {
    @Autowired
    private PlatformManager platformManager;

    /**
     * 查询所有平台名
     */
    @Test
    public void selectAllPlatformTest() {
        try {
            List<String> nameList = platformManager.selectAllPlatformName();
            log.info("查询所有平台名成功",nameList);
        } catch (Exception e) {
            log.info("查询所有平台名失败:",e);
        }
    }

    /**
     * 测试新增平台表记录
     */
    @Test
    public void insertPlatformTest() {
        try {
            Platform record = new Platform();
            record.setName("腾讯");
            record.setGroup(3);
            platformManager.insertPlatform(record);
            log.info("平台记录插入成功");
        } catch (Exception e) {
            log.info("平台记录插入失败:",e);
        }
    }


    /**
     * 根据姓名模糊查询所有匹配的平台记录(不分页)
     */
    @Test
    public void selectAllPlatformByNameTest() {
        try {
            Platform platform = new Platform();
            platform.setName("华腾");
            List<Platform> namelist = platformManager.selectAllPlatformByName(platform);
            log.info("根据姓名模糊查询平台记录成功",namelist);
        } catch (Exception e) {
            log.info("根据姓名模糊查询平台记录失败:",e);
        }
    }

    /**
     * 根据姓名模糊查询所有匹配的平台记录(分页)
     */
    @Test
    public void selectPlatformPageListByNameTest() {
        try {
            Page<List<Platform>> page = platformManager.selectPlatformPageListByName(new Platform(), new Pageable(1, 5));
            log.info("根据姓名模糊查询平台记录成功",page);
        } catch (Exception e) {
            log.info("根据姓名模糊查询平台记录失败:",e);
        }
    }




    /**
     * 根据主键更新平台表
     */
    @Test
    public void updatePlatformTest() {
        try {
            Platform platform = new Platform();
            platform.setPlatformId(1);
            Platform record = platformManager.selectPlatformById(platform);
            record.setGroup(10);
            platformManager.updatePlatform(record);
            log.info("更新平台记录成功");
        } catch (Exception e) {
            log.info("更新平台记录失败:",e);
        }
    }

    /**
     * 根据主键删除平台表对应记录
     */
    @Test
    public void deletePlatformTest(){
        try {
            Platform platform = new Platform();
            platform.setPlatformId(1);
            Platform record = platformManager.selectPlatformById(platform);
            platformManager.deletePlatform(record);
            log.info("删除平台记录成功");
        } catch (Exception e) {
            log.info("删除平台记录失败:",e);
        }

    }




}
