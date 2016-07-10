package com.bestpay.bpbp.signin.dal.mapper;


import com.bestpay.bpbp.signin.dal.models.Platform;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * PlatformMapper
 * Author 赵星
 * Version 1.0
 * Date 2016年5月20日10:19:17
 */
public interface PlatformMapper {

    /**
     * 新增平台表记录
     * @param record
     * @return
     */
    public void insertPlatform(Platform record) throws SQLException;

    /**
     * 根据姓名模糊查询所有匹配的平台记录(不分页)
     * @param platform
     * @return
     */

    public List<Platform> selectAllPlatformByName(Platform platform) throws SQLException;

    /**
     * 根据姓名模糊查询所有匹配的平台记录(分页)
     * @param map
     * @return
     */
    public List<Platform> selectPlatformPageListByName(Map map) throws SQLException;


    /**
     * 查询数据总条数
     * @param platform
     * @return
     */
    public int selectPlatformAllCount(Platform platform) throws SQLException;


    /**
     * 查询所有平台名
     * @param
     * @return
     */
    public List<String> selectAllPlatformName() throws SQLException;

    /**
     * 根据主键查询平台记录
     * @param platform
     * @return
     */
    public Platform selectPlatformById(Platform platform) throws SQLException;

    /**
     * 根据主键更新平台表
     * @param record
     * @return
     */
    public void updatePlatform(Platform record) throws SQLException;

    /**
     * 根据主键删除平台表对应记录
     * @param record
     */
    public void deletePlatform(Platform record) throws SQLException;
}
