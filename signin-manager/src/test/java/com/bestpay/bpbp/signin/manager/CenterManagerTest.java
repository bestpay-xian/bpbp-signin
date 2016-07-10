/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.base.BaseSpringTest;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Center;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * CenterManagerTest
 * Author 赵星
 * Version 1.0
 * Date 2016年5月20日10:20:10
 */
@Slf4j
public class CenterManagerTest extends BaseSpringTest {
    @Autowired
    private CenterManager centerManager;

    /**
     * 新增中心表记录
     */
    @Test
    public void insertCenterTest() {
        try {
            for (int i = 1; i < 10; i++){
                Center center = new Center();
                center.setCenterName("支撑中心" + i);
                center.setDeptId(i);
                centerManager.insertCenter(center);
            }
            log.info("中心记录插入成功");
        } catch (Exception e) {
            log.error("中心记录插入失败:",e);
        }
    }


    /**
     * 按条件查询中心表记录(不分页)
     */
    @Test
    public void selectCenterListTest() {
        try {
            List<Center> centerlist = centerManager.selectCenterList(new Center());
            log.info("不分页查询中心记录成功",centerlist);
        } catch (Exception e) {
            log.info("不分页查询中心记录失败:",e);
        }
    }

    /**
     * 按条件查询中心表记录(分页)
     */
    @Test
    public void selectCenterPageListTest() {
        try {
            Page<List<Center>> page =  centerManager.selectCenterPageList(new Center(), new Pageable(1,5));
            log.info("分页查询中心记录成功",page);
        } catch (Exception e) {
            log.info("分页查询中心记录成功:",e);
        }
    }



    /**
     * 更新中心表记录
     */
    @Test
    public void updateCenterTest() {
        try {
            Center center = new Center();
            center.setCenterId(1);
            center.setCenterName("更改中心名");
            centerManager.updateCenter(center);
            log.info("更新中心表记录成功");
        } catch (Exception e) {
            log.error("更新中心表记录失败:",e);
        }
    }

    /**
     * 根据主键删除中心表记录
     */
    @Test
    public void deleteCenterTest(){
        try {
            Center center = new Center();
            center.setCenterId(3);
            centerManager.deleteCenter(center);
            log.info("删除中心记录成功");
        } catch (Exception e) {
            log.info("删除中心记录失败:",e);
        }

    }


    /**
     * 根据主键查询中心表记录
     */
    @Test
    public void selectCenterByIdTest(){
        try {
            Center center = new Center();
            center.setCenterId(14);
            Center center1 =  centerManager.selectCenterById(center);
            log.info("根据主键查询中心记录成功",center1);
        } catch (Exception e) {
            log.info("根据主键查询中心记录失败:",e);
        }

    }


}
