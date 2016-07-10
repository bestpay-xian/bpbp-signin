package com.bestpay.bpbp.signin.dal.mapper;


import com.bestpay.bpbp.signin.dal.models.Center;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * CenterMapper
 * Author 赵星
 * Version 1.0
 * Date 2016年5月23日11:36:22
 */
public interface CenterMapper {

    /**
     * 新增中心表记录
     * @param center
     * @return
     */
    public void insertCenter(Center center) throws SQLException;

    /**
     * 按条件查询中心表记录(不分页)
     * @param center
     * @return
     */

    public List<Center> selectCenterList(Center center) throws SQLException;

    /**
     * 按条件查询中心表记录(分页)
     * @param map
     * @return
     */
    public List<Center> selectCenterPageList(Map map) throws SQLException;


    /**
     * 查询数据总条数
     * @param center
     * @return
     */
    public int selectCenterCount(Center center) throws SQLException;


    /**
     * 根据主键查询中心表记录
     * @param center
     * @return
     */
    public Center selectCenterById(Center center) throws SQLException;

    /**
     * 更新中心表记录
     * @param center
     * @return
     */
    public void updateCenter(Center center) throws SQLException;

    /**
     * 根据主键删除中心表记录
     * @param center
     */
    public void deleteCenter(Center center) throws SQLException;
}
