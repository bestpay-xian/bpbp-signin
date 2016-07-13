package com.bestpay.bpbp.signin.web.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Dept;
import com.bestpay.bpbp.signin.service.signservice.DeptService;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;

/**
 * DeptController
 * Author liyang
 * Version 1.0
 * Date 2016年5月23日10:20:59
 */
@Slf4j
@Controller
@RequestMapping
public class DeptController {

    /**
     * 注入部门管理service
     */
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门信息 Ajax,分页
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.QUERY_DEPT_URL, method = RequestMethod.POST)
    public Message findDeptList(Dept dept, Pageable pageable) {
        log.info("部门管理-列表查询入参:{}", dept);
        try {
            return Message.successRst(deptService.findDeptList(dept, pageable), "查询成功");
        } catch (Exception e) {
            log.error("部门管理-列表查询错误信息:{}", e);
            return Message.errorRst("查询失败");
        }
    }

    /**
     * 查询部门信息 form,分页
     * @param dept
     * @return
     */
    @RequestMapping(value = ApiUrls.QUERY_DEPT_FORM_URL)
    public String findDeptList(Dept dept, Pageable pageable, Model model) {
        log.info("部门管理-列表查询入参:{}", dept);
        try {
            model.addAttribute("list", deptService.findDeptList(dept, pageable));
            return ApiUrls.QUERY_DEPT_JSP;
        } catch (Exception e) {
            model.addAttribute("message", "列表查询失败请重试!");
            log.error("部门管理-列表查询错误信息:{}", e);
            return ApiUrls.LAYOUT_INDEX_JSP;
        }
    }

    /**
     * 跳转新增部门界面
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.SAVE_DEPT_URL)
    public String saveDept(Model model) {
        log.info("部门管理-页面跳转:{}", "跳转到部门新增界面");
        try {
            return ApiUrls.ADD_DEPT_JSP;
        } catch (Exception e) {
            log.error("部门管理-界面跳转错误信息:{}", e);
            return ApiUrls.QUERY_DEPT_JSP;
        }
    }

    /**
    * 保存部门信息
    * @param dept
    * @return
    */
    @RequestMapping(value = ApiUrls.INSERT_DEPT_URL, method = RequestMethod.POST)
    public String insertDept(Dept dept, Model model) {
        log.info("部门管理-部门保存入参:{}", dept);
        try {
            deptService.insertDept(dept);
            return "redirect:"+ApiUrls.QUERY_DEPT_FORM_URL +".do";
//            return ApiUrls.QUERY_DEPT_JSP;
        } catch (Exception e) {
            log.error("部门管理-部门保存错误信息:{}", e);
            model.addAttribute("message", "插入失败请重试");
            return ApiUrls.ADD_DEPT_JSP;
        }
    }

    /**
     * 跳转部门修改界面
     * @param dept
     * @return
     */
    @RequestMapping(value = ApiUrls.EDIT_DEPT_URL, method = RequestMethod.POST)
    public String editDept(Dept dept, Model model) {
        log.info("部门管理-部门修改:{},入参:{}", "跳转jsp", dept);
        try {
            model.addAttribute("dept", deptService.findDept(dept));model.addAttribute("dept", deptService.findDept(dept));
            return ApiUrls.UPDATE_DEPT_JSP;
        } catch (Exception e) {
            model.addAttribute("message","查询失败");
            log.error("部门管理-部门修改跳转jsp错误信息:{}", e);
            return ApiUrls.QUERY_DEPT_JSP;
        }
    }

    /**
    * 部门信息修改
    * @param dept
    * @return
    */
    @RequestMapping(value = ApiUrls.UPDATE_DEPT_URL, method = RequestMethod.POST)
    public String updateDept(Dept dept, Model model) {
        log.info("部门管理-部门修改入参:{}", dept);
        try {
            deptService.updateDept(dept);
            return "redirect:"+ApiUrls.QUERY_DEPT_FORM_URL +".do";
        } catch (Exception e) {
            log.error("部门管理-部门修改错误信息:{}", e);
            model.addAttribute("message", "插入失败请重试");
            return ApiUrls.UPDATE_DEPT_JSP;
        }
    }

    /**
     * 删除部门信息(is_delete=2)
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.DELETE_DEPT_URL, method = RequestMethod.POST)
    public Message deleteDept(Dept dept) {
        log.info("部门管理-删除部门入参:{}", dept);
        try {
            String deleteInfo=deptService.deleteDept(dept);
            return Message.successRst(dept,deleteInfo);
        } catch (Exception e) {
            log.error("部门管理-删除部门错误信息:{}", e);
            return Message.errorRst("删除部门失败");
        }
    }

    /**
     * 检查信息是否存在
     * @param dept
     * @return  false 信息已存在  true 信息不存在
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.CHECK_DEPT_URL, method = RequestMethod.POST)
    public Boolean checkDept(Dept dept) {
        log.info("部门管理-查询部门信息是否存在入参:{}", dept);
        try {
            return deptService.checkDeptList(dept);
        } catch (Exception e) {
            log.error("部门管理-查询部门信息是否存在错误信息:{}", dept);
            return false;
        }
    }
}
