package com.bestpay.bpbp.signin.web.controller;

import com.bestpay.bpbp.signin.common.PassTools;
import com.bestpay.bpbp.signin.dal.models.Employee;
import com.bestpay.bpbp.signin.service.signservice.LoginService;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LoginController
 * Author Liyang
 * Version 1.0
 * Date 2016/5/18 14:41
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录信息
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = ApiUrls.LOGIN_URL, method = RequestMethod.POST)
    public String login(String username, String password, HttpSession httpSession, Model model) {

        log.info("登录管理-请求入参{},{}", username, password);
        if (username == null || "".equals(username)) {
            model.addAttribute("message", "用户名为空");
            return ApiUrls.LOGIN_JSP;
        }
        if (password == null || "".equals(password)) {
            model.addAttribute("message", "密码为空");
            return ApiUrls.LOGIN_JSP;
        }

        //modify by linxing start
        String encryptUserName = PassTools.encryptStrinrgByMD5(username);
        String finalPasswordForLogin = encryptUserName + password;
        log.info("登录管理---密码验证---用户名{}密码{}FINAL密码{}", encryptUserName, password, finalPasswordForLogin);
        //modify by linxing end

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", username);
        try {
            List<Employee> employeeList = loginService.selectLogin(map);
            if (employeeList == null || employeeList.size() == 0 || employeeList.size() >= 2) {
                model.addAttribute("message", "用户名不存在");
                return ApiUrls.LOGIN_JSP;
            }
            Employee employeeObject = employeeList.get(0);
            //modify by linxing  password --> finalPasswordForLogin
            if (!employeeObject.getPassword().equals(finalPasswordForLogin)) {
                model.addAttribute("message", "密码不正确");
                return ApiUrls.LOGIN_JSP;
            }
            //添加用户信息到session中
            httpSession.setAttribute("employee", employeeObject);
            log.info("登录管理-业务查询结果{}", employeeObject);
            return ApiUrls.LAYOUT_JSP;
        } catch (Exception e) {
            log.error("登录管理-错误信息{}", e);
            model.addAttribute("message", "操作失败，请重试");
            return ApiUrls.LOGIN_JSP;
        }
    }

    /**
     * 用户退出
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(value = ApiUrls.LOGOUT_URL)
    public String logout(HttpSession httpSession) {
        log.info("登录管理-用户退出系统{}", (Employee) httpSession.getAttribute("employee"));
        httpSession.setMaxInactiveInterval(0);
        return ApiUrls.LOGIN_JSP;
    }

}
