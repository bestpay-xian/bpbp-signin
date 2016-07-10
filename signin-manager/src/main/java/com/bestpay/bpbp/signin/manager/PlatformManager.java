package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.mapper.PlatformMapper;
import com.bestpay.bpbp.signin.dal.models.Platform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * PlatformManager
 * Author 赵星
 * Version 1.0
 * Date 2016年5月20日10:18:44
 */
@Slf4j
@Component
public class PlatformManager {

    /**
     * platform模块PlatformManager层注入PlatformMapper依赖
     */
    @Autowired
    public PlatformMapper platformMapper;

    /**
     * 新增平台表记录
     * @param record
     * @return
     */
    public void insertPlatform(Platform record) throws Exception {
        platformMapper.insertPlatform(record);
    }

    /**
     * 根据姓名模糊查询所有匹配的平台记录（不分页）
     * @param platform
     * @return
     */
    public List<Platform> selectAllPlatformByName(Platform platform) throws SQLException{
        return platformMapper.selectAllPlatformByName(platform);
    }

    /**
     * 根据姓名模糊查询所有匹配的平台记录（分页）
     * @param platform
     * @return
     */
    public Page<List<Platform>> selectPlatformPageListByName(Platform platform,Pageable pageable) throws Exception{
        Map map = BeanUtils.describe(platform);
        map.put("pageNo", pageable.getPageNo());
        map.put("rowNum",pageable.getRowNum());
        return new Page(platformMapper.selectPlatformPageListByName(map),platformMapper.selectPlatformAllCount(platform),pageable);
    }

    /**
     * 查询所有平台名
     * @param
     * @return
     */
    public List<String> selectAllPlatformName() throws SQLException{
        return platformMapper.selectAllPlatformName();
    }

    /**
     * 根据主键查询平台记录
     * @param platform
     * @return
     */
    public Platform selectPlatformById(Platform platform) throws SQLException{
        return platformMapper.selectPlatformById(platform);
    }


    /**
     * 根据主键更新平台表
     * @param record
     * @return
     */
    public void updatePlatform(Platform record) throws SQLException{
        platformMapper.updatePlatform(record);
    }

    /**
     * 根据主键删除平台表对应记录
     * @param record
     */
    public void deletePlatform(Platform record) throws SQLException{
        platformMapper.deletePlatform(record);
    }
}
