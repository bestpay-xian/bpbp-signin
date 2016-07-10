package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.manager.PlatformManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PlatformService
 * Author 赵星
 * Version 1.0
 * Date 2016年5月23日09:56:24
 */
@Service
public class PlatformService {
    /**
     * platform模块PlatformService层注入PlatformManager依赖
     */
    @Autowired
    private PlatformManager platformManager;


    /**
     * 新增平台表记录
     * @param record
     * @return
     */
    public void insertPlatform(Platform record) throws Exception {
        platformManager.insertPlatform(record);
    }

    /**
     * 根据姓名模糊查询获取所有匹配平台记录（不分页）
     * @param platform
     * @return
     */
    public List<Platform> selectAllPlatformByName(Platform platform) throws Exception{
        return platformManager.selectAllPlatformByName(platform);
    }

    /**
     * 2016年5月23日09:52:23
     * 根据姓名模糊查询获取所有匹配平台记录（分页）
     * @param platform
     * @param pageable
     * @return
     */
    public Page<List<Platform>> selectPlatformPageListByName(Platform platform,Pageable pageable) throws Exception{
        return platformManager.selectPlatformPageListByName(platform,pageable);
    }

    /**
     * 获取所有平台名
     * @param
     * @return
     */
    public List<String>  selectAllPlatformName() throws Exception{
        return platformManager.selectAllPlatformName();
    }


    /**
     * 根据主键更新平台表
     * @param record
     * @return
     */
    public void updatePlatformByPrimaryKey(Platform record) throws Exception{
        platformManager.updatePlatform(record);
    }

    /**
     * 根据主键删除平台表对应记录
     * @param record
     */
    public void deletePlatformByPrimaryKey(Platform record) throws Exception{
        platformManager.deletePlatform(record);
    }



    /**
     * 根据主键查询平台表对应记录
     * @param record
     */
    public Platform selectPlatformById(Platform record) throws Exception{
        return platformManager.selectPlatformById(record);
    }
}
