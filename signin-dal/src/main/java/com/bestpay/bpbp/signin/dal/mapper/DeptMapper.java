package com.bestpay.bpbp.signin.dal.mapper;


import com.bestpay.bpbp.signin.dal.models.Dept;
import com.bestpay.bpbp.signin.dal.models.Platform;

import java.util.List;
import java.util.Map;

/**
 * Author 李洋
 * Version 1.0
 * Date 2016年5月23日10:19:17
 */
public interface DeptMapper {

    /**
     * 新增部门记录
     * @param dept
     * @return
     */
    public int insertDept(Dept dept);

    /**
     * 根据查询所有匹配的部分记录(不分页)
     * @param dept
     * @return
     */

    public List<Dept> selectAllDept(Dept dept);

    /**
     * 根据姓名模糊查询所有匹配的部门记录(分页)
     * @param map
     * @return
     */
    public List<Dept> selectDeptPageList(Map map) throws Exception;


    /**
     * 查询数据总条数
     * @param dept
     * @return
     */
    public int selectDeptAllCount(Dept dept);

    /**
     * 根据主键更新部门表
     * @param dept
     * @return
     */
    public void updateDept(Dept dept);

    /**
     * 根据主键删除平台表对应记录
     * @param dept
     */
    public void deleteDept(int dept);
    /**
     * 检查部门信息是否存在
     * @param dept
     */
    public List<Dept> checkDept(Dept dept);
}
