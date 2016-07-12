/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.dal.mapper;

import com.bestpay.bpbp.signin.dal.models.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * EmployeeMapper
 * Author Liyang
 * Version 1.0
 * Date 2016/5/16 14:40
 */
public interface EmployeeMapper {

    /**
     * 查询员工信息表 不分页
     * @return
     * @throws SQLException
     */
    public List<Employee> selectEmployee(Employee employee) throws SQLException;
    /**
     * 查询员工信息表
     * @return
     * @throws SQLException
     */
    public List<Employee> selectEmployeeList(Map map) throws SQLException;
    /**
     * 查询员工信息表
     * @return
     * @throws SQLException
     */
    public int selectEmployeeAllCount(Employee employee) throws SQLException;

    /**
     * 查询信息是否存在
     * @return
     * @throws SQLException
     */
    public List<Employee> checkEmployeeList(Map map) throws SQLException;

    /**
     * 员工登录
     * @param map
     * @return
     * @throws SQLException
     */
    public List<Employee> selectLogin(Map<String, Object> map) throws SQLException;

    /**
     * 保存员工信息表
     * @param employee
     * @throws SQLException
     */
    public int insertEmployee(Employee employee) throws SQLException;

    /**
     * 修改员工信息表
     * @param employee
     * @throws SQLException
     */
    public void updateEmployee(Employee employee) throws SQLException;

    /**
     * 删除员工信息表,修改 isDelete=1
     * @param employeeId
     * @throws SQLException
     */
    public void deleteEmployee(int employeeId) throws SQLException;
    /**
     * 重置用户登录密码
     *
     * @param employee
     * @throws SQLException
     */
    public void resetPassword(Employee employee)throws SQLException;
    /**
     * 通讯录信息查看,分页
     */
    public List<Map<String, Object>> selectAllInfo(Map map) throws SQLException;

    /**
     * 返回通讯录结果集 数据总条数
     * @param employee
     * @return 通讯录结果集
     * @throws SQLException
     */
    public int selectAllCount(Employee employee) throws SQLException;

}