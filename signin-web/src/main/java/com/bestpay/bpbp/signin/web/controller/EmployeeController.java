package com.bestpay.bpbp.signin.web.controller;

import javax.servlet.http.HttpSession;

import com.bestpay.bpbp.signin.common.PassTools;
import com.bestpay.bpbp.signin.common.utils.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Employee;
import com.bestpay.bpbp.signin.service.signservice.EmployeeService;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoginController
 * Author Liyang
 * Version 1.0
 * Date 2016/5/18 10:03
 */
@Slf4j
@Controller
public class EmployeeController {

    /**
     * 注入用户管理service
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询用户信息 Ajax
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.QUERY_EMPLOYEE_URL, method = RequestMethod.POST)
    public Message findEmployeeList(Employee employee, Pageable pageable) {
        log.info("用户管理-用户列表查询查询入参:{}", employee);
        try {
            return Message.successRst(employeeService.findEmployeeList(employee, pageable), "查询成功");
        } catch (Exception e) {
            log.error("用户管理-用户列表查询错误信息:{}", e);
            return Message.errorRst("查询失败");
        }
    }

    /**
     * 查询用户信息 from
     * @param employee
     * @return
     */
    @RequestMapping(value = ApiUrls.QUERY_EMPLOYEE_FORM_URL)
    public String findEmployeeFormList(Employee employee, Pageable pageable, Model model) {
        log.info("用户管理-用户列表查询查询入参:{}", employee);
        try {
            model.addAttribute("list", employeeService.findEmployeeList(employee, pageable));
            return ApiUrls.QUERY_EMPLOYEE_JSP;
        } catch (Exception e) {
            log.error("用户管理-用户列表查询错误信息:{}", e);
            return ApiUrls.LAYOUT_INDEX_JSP;
        }
    }

    /**
     * 跳转新增用户界面
     * @param
     * @return
     */
    @Token(save=true)
    @RequestMapping(value = ApiUrls.SAVE_EMPLOYEE_URL)
    public String saveEmployee(Model model) {
        log.info("用户管理-新增用户:{}", "跳转到用户新增界面");

        try {
            return ApiUrls.ADD_EMPLOYEE_JSP;
        } catch (Exception e) {
            log.error("用户管理-新增用户页面跳转错误信息:{}", e);
            return ApiUrls.QUERY_EMPLOYEE_JSP;
        }
    }

    /**
    * 保存用户信息
    * @param employee
    * @return
    */
    @Token(remove=true)
    @RequestMapping(value = ApiUrls.INSERT_EMPLOYEE_URL, method = RequestMethod.POST)
    public String insertEmployee(Employee employee, Model model) {
        log.info("用户管理-新增用户保存用户入参:{}", employee);
        try {
            //modify by linxing start
            //1 ----> 普通用户  2 ----->管理员 此方法只能新增普通用户
            employee.setIsAdmin(1);
            String encryptUserName ="";
            if(null != employee.getEmail()){
                encryptUserName = PassTools.encryptStrinrgByMD5(employee.getEmail());
            }
            String finalPasswordForInsert = encryptUserName + employee.getPassword();
            log.info("用户管理---用户名{}密码{}FINAL密码{}", employee.getEmail(), employee.getPassword(), finalPasswordForInsert);
            employee.setPassword(finalPasswordForInsert.toUpperCase());
            //employee.setPassword(employee.getPassword().toUpperCase());
            //modify by linxing end

            int employeeId = employeeService.insertEmployee(employee);

            return  "redirect:/"+ApiUrls.QUERY_EMPLOYEE_FORM_URL+".do";
        } catch (Exception e) {
            log.error("用户管理-新增用户保存错误信息:{}", e);
            model.addAttribute("message", "插入失败请重试");
            return ApiUrls.ADD_EMPLOYEE_JSP;
        }
    }

    /**
     * 跳转修改界面
     * @param employee
     * @return
     */
    @RequestMapping(value = ApiUrls.EDIT_EMPLOYEE_URL, method = RequestMethod.POST)
    public String editEmployee(Employee employee, Model model) {
        log.info("用户管理-修改用户:{},入参:{}", "跳转修改jsp", employee);
        try {
            model.addAttribute("employee", employeeService.findEmployee(employee).get(0));
            return ApiUrls.UPDATE_EMPLOYEE_JSP;
        } catch (Exception e) {
            log.error("用户管理-修改用户跳转错误信息:{}", e);
            return ApiUrls.QUERY_EMPLOYEE_JSP;
        }
    }

    /**
     * 用户信息修改
     * @param employee
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_EMPLOYEE_URL, method = RequestMethod.POST)
    public String updateEmployee(Employee employee, Model model) {
        log.info("用户管理-修改用户入参:{}", employee);
        try {
            employeeService.updateEmployee(employee);
          return "redirect:/"+ApiUrls.QUERY_EMPLOYEE_FORM_URL +".do";
        } catch (Exception e) {
            log.error("用户管理-修改用户错误信息:{}", e);
            model.addAttribute("message", "插入用户失败请重试");
            return ApiUrls.UPDATE_EMPLOYEE_JSP;
        }
    }

    /**
     * 用户信息修改
     * @param employee
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_PASSWORD_URL, method = RequestMethod.POST)
    public String updatePassword(Employee employee, Model model, HttpSession httpSession) {
        log.info("用户管理-密码修改入参:{}", employee);
        try {
            Employee employeeOld = (Employee) httpSession.getAttribute("employee");
            employee.setEmployeeId(employeeOld.getEmployeeId());
            String encryptUserName = "";
            if(null != employeeOld.getEmail()){
                encryptUserName = PassTools.encryptStrinrgByMD5(employeeOld.getEmail());
            }
            String finalPasswordForUpdate = encryptUserName + employee.getPassword();
            employee.setPassword(finalPasswordForUpdate.toUpperCase());

            employeeService.updateEmployee(employee);
            return ApiUrls.LAYOUT_INDEX_JSP;
        } catch (Exception e) {
            log.error("用户管理-密码修改错误信息:{}", e);
            model.addAttribute("message", "修改密码失败请重试");
            return ApiUrls.UPDATE_PASSWORD_JSP;
        }
    }

    /**
     * 删除用户信息(is_delete =2)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.DELETE_EMPLOYEE_URL, method = RequestMethod.POST)
    public Message deleteEmployee(int employeeId) {
        log.info("用户管理-用户删除入参:{}", employeeId);
        try {
            employeeService.deleteEmployee(employeeId);
            return Message.successRst("删除用户成功");
        } catch (Exception e) {
            log.error("用户管理-用户删除错误信息:{}", e);
            return Message.errorRst("删除用户失败");
        }
    }
    /**
     * 重置用户密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value=ApiUrls.RESET_PASSWORD_URL,method = RequestMethod.POST)
    public Message restPassword(Employee employee) {
        log.info("用户管理-用户登录密码重置入参:{}", employee);
        try {
            String encryptUserName = "";
            if(null != employee.getEmail()){
                encryptUserName = PassTools.encryptStrinrgByMD5(employee.getEmail());
            }
            String password = "000000";
            String encyptPwd = PassTools.encryptStrinrgByMD5(password);
            String finalPasswordForUpdate = encryptUserName+encyptPwd;
            String str=finalPasswordForUpdate;
            String str1 = str.substring(0,str.length()-1);
            employee.setPassword(str1.toUpperCase());
            employeeService.resetPassword(employee);
            return Message.successRst("用户登录密码重置成功");
        } catch (Exception e) {
            log.error("用户管理-用户登录密码重置失败信息:{}", e);
            return Message.errorRst("用户登录密码重置失败");
        }
    }
    /**
     * 检查信息是否存在
     * @param employee
     * @return  false 信息已存在  true 信息不存在
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.CHECK_EMPLOYEE_URL, method = RequestMethod.POST)
    public Boolean checkEmployee(Employee employee) {
        log.info("用户管理-检查用户信息是否存在查询入参:{}", employee);
        try {
            return employeeService.checkEmployeeList(employee);
        } catch (Exception e) {
            log.error("用户管理-检查用户信息是否存在错误信息:{}", e);
            return false;
        }
    }

    /**
     * 查询通讯录信息-分页信息
     * @param employee
     * @param pageable
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.QUERY_ALL_URL)
    public Message findAllInfo(Employee employee, Pageable pageable) {
        log.info("通讯录管理-列表查询入参:{}", employee);
        try {
            return Message.successRst(employeeService.selectAllInfo(employee, pageable), "通讯录查询成功");
        } catch (Exception e) {
            log.error("通讯录管理-列表查询错误信息:{}", e);
            return Message.errorRst("通讯录查询失败");
        }
    }

    /**
     * 查询通讯录信息 from
     * @param employee
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = ApiUrls.QUERY_ALL_FROM_URL)
    public String findCommFromList(Employee employee, Pageable pageable, Model model) {
        log.info("查询通讯录-通讯录参数查询入参：{}", employee);
        try {
            model.addAttribute("list", employeeService.selectAllInfo(employee, pageable));
            return ApiUrls.QUERY_COMM_JSP;
        } catch (Exception e) {
            log.error("查询通讯录-通讯录查询错误信息：{}",e);
            return ApiUrls.QUERY_COMM_JSP;
        }

    }
}
