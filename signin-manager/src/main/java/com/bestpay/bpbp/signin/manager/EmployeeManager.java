package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.mapper.EmployeeMapper;
import com.bestpay.bpbp.signin.dal.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmployeeManager
 * Author Liyang
 * Version 1.0
 * Date 2016/5/16 14:40
 */
@Slf4j
@Component
public class EmployeeManager {

    @Autowired
    public EmployeeMapper employeeMapper;

    /**
     * 查询总数据量
     * @param employee
     * @return
     * @throws Exception
     */
    public long selectEmployeeAllCount(Employee employee) throws Exception{
        return employeeMapper.selectEmployeeAllCount(employee);

    }

    /**
     * 查询分页信息
     * @param map 查询参数，页码，页数
     * @return
     * @throws Exception
     */
    public List<Employee> selectEmployeeList(Map map) throws Exception{
        return employeeMapper.selectEmployeeList(map);

    }

    /**
     * 查询用户信息 不分页
     * @param employee
     * @return
     * @throws Exception
     */
    public List<Employee> selectEmployee(Employee employee) throws Exception{
        return employeeMapper.selectEmployee(employee);

    }
    /**
     * 检查用户信息是否存在
     * @return List<Employee>
     * @throws Exception
     */
    public List<Employee> checkEmployeeList(Map map) throws Exception{
        return employeeMapper.checkEmployeeList(map);
    }

    /**
     * 删除用户信息
     * @param employeeId
     * @throws Exception
     */
    public void deleteEmployee(int employeeId) throws Exception{
        employeeMapper.deleteEmployee(employeeId);
    }

    /**
     * 保存用户信息
     * @param employee
     * @throws Exception
     */
    public int insertEmployee(Employee employee)throws Exception{
        return employeeMapper.insertEmployee(employee);
    }

    /**
     * 修改用户信息
     * @param employee
     * @throws Exception
     */
    public void updateEmployee(Employee employee)throws Exception{
        employeeMapper.updateEmployee(employee);
    }

    /**
     * 查询通讯录信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectAllInfo (Map map) throws Exception{
        return employeeMapper.selectAllInfo(map);
    }
    /**
     * 查询通讯录信息,返回数据总条数
     * @param employee
     * @return
     * @throws Exception
     */
    public int selectAllCount (Employee employee) throws Exception{
        return employeeMapper.selectAllCount(employee);
    }
}
