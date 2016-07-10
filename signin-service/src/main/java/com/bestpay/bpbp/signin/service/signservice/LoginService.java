package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.dal.mapper.EmployeeMapper;
import com.bestpay.bpbp.signin.dal.models.Employee;
import com.bestpay.bpbp.signin.manager.LoginManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * LoginService
 * Author Liyang
 * Version 1.0
 * Date 2016/5/23 9:37
 */
@Slf4j
@Service
public class LoginService {

    /**
     * 注入登录管理manager
     */
    @Autowired
    public LoginManager loginManager;

    /**
     * 查询登录信息
     * @return Employee
     * @throws Exception
     */
    public List<Employee> selectLogin(Map<String,Object> map) throws Exception{
        return loginManager.selectLogin(map);
    }

}
