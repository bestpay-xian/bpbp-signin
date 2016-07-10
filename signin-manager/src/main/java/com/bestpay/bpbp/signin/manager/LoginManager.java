package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.dal.mapper.EmployeeMapper;
import com.bestpay.bpbp.signin.dal.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * LoginManager
 * Author Liyang
 * Version 1.0
 * Date 2016/5/23 9:38
 */
@Slf4j
@Component
public class LoginManager {

    @Autowired
    public EmployeeMapper employeeMapper;

    /**
     * 查询登录信息
     * @return Employee
     * @throws Exception
     */
    public List<Employee> selectLogin(Map<String,Object> map) throws Exception{
        return employeeMapper.selectLogin(map);
    }

}
