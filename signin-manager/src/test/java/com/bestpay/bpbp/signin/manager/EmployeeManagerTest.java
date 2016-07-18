/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.manager;


import com.bestpay.bpbp.signin.base.BaseSpringTest;
import com.bestpay.bpbp.signin.dal.mapper.EmployeeMapper;
import com.bestpay.bpbp.signin.dal.models.Employee;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Weiliang
 * @version $Id: EmployeeManagerTest.java, v 0.1 2016年1月26日 下午4:39:43 Weiliang Exp $
 */
@Slf4j
@Component
public class EmployeeManagerTest extends BaseSpringTest {

    @Autowired
    public EmployeeManager employeeManager;

    /**
     * 查询总数据量
     * @return
     * @throws Exception
     */
    @Test
    public void selectEmployeeAllCount() throws Exception{
        Employee employee = new Employee();
        employee.setName("admin");
        employeeManager.selectEmployeeAllCount(employee);

    }

    /**
     * 查询分页信息
     * @return
     * @throws Exception
     */
    @Test
    public void selectEmployeeList() throws Exception{
        Map map = new HashMap();
        map.put("deptName","admin");
        map.put("pageNo", 0);
        map.put("rowNum", 5);
        employeeManager.selectEmployeeList(map);

    }

    /**
     * 查询用户信息 不分页
     * @return
     * @throws Exception
     */
    @Test
    public void selectEmployee() throws Exception{
        Employee employee = new Employee();
        employee.setName("admin");
        employeeManager.selectEmployee(employee);

    }
    /**
     * 检查用户信息是否存在
     * @return List<Employee>
     * @throws Exception
     */
    @Test
    public void checkEmployeeList() throws Exception{
        Map map = new HashMap();
        map.put("email","333@qq.com");
        employeeManager.checkEmployeeList(map);
    }

    /**
     * 删除用户信息
     * @throws Exception
     */
    @Test
    public void deleteEmployee() throws Exception{
        employeeManager.deleteEmployee(1);
    }

    /**
     * 保存用户信息
     * @throws Exception
     */
    @Test
    public void insertEmployee()throws Exception{
        Employee employee = new Employee();
        employee.setEmail("12312@qq.com");
        employee.setQq("121212121");
        employee.setName("测试");
        employee.setPassword("1231231231");
        employee.setPhone("123131231");
        employeeManager.insertEmployee(employee);
    }

    /**
     * 修改用户信息
     * @throws Exception
     */
    @Test
    public void updateEmployee()throws Exception{
        Employee employee = new Employee();
        employee.setEmail("12312@qq.com");
        employee.setEmployeeId(1);
        employeeManager.updateEmployee(employee);
    }

    /**
     * 查询通讯录信息
     * @return
     * @throws Exception
     */
    @Test
    public void selectAllInfo () throws Exception{
        Map map = new HashMap();
        map.put("name","admin");
        map.put("pageNo", 0);
        map.put("rowNum", 5);
        employeeManager.selectAllInfo(map);
    }
    /**
     * 查询通讯录信息,返回数据总条数
     * @return
     * @throws Exception
     */
    @Test
    public void selectAllCount() throws Exception{
        Employee employee = new Employee();
        employee.setName("admin");
        employeeManager.selectAllCount(employee);
    }
}
