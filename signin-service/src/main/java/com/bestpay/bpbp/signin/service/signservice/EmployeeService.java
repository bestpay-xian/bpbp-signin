package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Employee;
import com.bestpay.bpbp.signin.manager.EmployeeManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmployeeService
 * Author Liyang
 * Version 1.0
 * Date 2016/5/23 9:45
 */
@Slf4j
@Service
public class EmployeeService {

    /**
     * 注入用户管理manager
     */
    @Autowired
    private EmployeeManager employeeManager;

    /**
     *
     * @param employee 查询条件
     * @param pageable 分页信息
     * @return
     * @throws Exception
     */
    public Page<Employee> findEmployeeList(Employee employee,Pageable pageable) throws Exception{
        //将Employee转Map
        Map map = BeanUtils.describe(employee);
        map.put("pageNo", pageable.getPageNo()*pageable.getRowNum());
        map.put("rowNum",pageable.getRowNum());
        return new Page(employeeManager.selectEmployeeList(map),employeeManager.selectEmployeeAllCount(employee),pageable);

    }

    /**
     * 查询条件 不分页
     * @param employee
     * @param
     * @return
     * @throws Exception
     */
    public List<Employee> findEmployee(Employee employee) throws Exception{
        return employeeManager.selectEmployee(employee);

    }
    /**
     * 检查用户信息是否存在
     * @return boolean
     * @throws Exception
     */
    public boolean checkEmployeeList(Employee employee) throws Exception{
        Map map = new HashMap();
        int qq = employee.getQq();
        if(qq !=0){
            map.put("qq",employee.getQq());
        }
        String phone = employee.getPhone();
        if(phone !=null && !"".equals(phone)){
            map.put("phone",employee.getPhone());
        }
        String email = employee.getEmail();
        if(email != null && !"".equals(email)){
            map.put("email",email);
        }
        int employeeId = employee.getEmployeeId();
        if(employeeId !=0){
            map.put("employeeId",employeeId);
        }
        int employeeSize = employeeManager.checkEmployeeList(map).size();
        if(employeeSize > 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 删除用户信息
     * @param employeeId
     * @throws Exception
     */
    public void deleteEmployee(int employeeId) throws Exception{
        employeeManager.deleteEmployee(employeeId);
    }

    /**
     * 保存用户信息
     * @param employee
     * @throws Exception
     */
    public int insertEmployee(Employee employee)throws Exception{
        return employeeManager.insertEmployee(employee);
    }

    /**
     * 修改用户信息
     * @param employee
     * @throws Exception
     */
    public void updateEmployee(Employee employee)throws Exception{
        employeeManager.updateEmployee(employee);
    }

    /**
     * 查询通讯录信息
     * @param employee
     * @return List<Map<String,Object>> 所有与员工关联信息
     * @throws Exception
     */
    public Page selectAllInfo(Employee employee,Pageable pageable) throws Exception{
        //将Employee转Map
        Map map = BeanUtils.describe(employee);
        map.put("pageNo", pageable.getPageNo()*pageable.getRowNum());
        map.put("rowNum",pageable.getRowNum());
        return new Page(employeeManager.selectAllInfo(map),employeeManager.selectAllCount(employee),pageable);
    }

}
