package com.bestpay.bpbp.signin.web.controller;

import com.bestpay.bpbp.signin.dal.models.Employee;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * LayoutController跳转成功后，返回布局页面
 * Author Liyang
 * Version 1.0
 * Date 2016/5/19 20:45
 */
@Slf4j
@Controller
public class LayoutController {


    /**
     * 返回布局top页面
     * @param model
     * @return
     */
    @RequestMapping(value=ApiUrls.LAYOUT_TOP_URL)
    public String layoutTop(Model model){
        return ApiUrls.LAYOUT_TOP_JSP;
    }

    /**
     * 返回布局index页面
     * @param model
     * @return
     */
    @RequestMapping(value=ApiUrls.LAYOUT_INDEX_URL)
    public String layoutIndex(Model model){
        return ApiUrls.LAYOUT_INDEX_JSP;
    }

    /**
     * 返回布局footer页面
     * @param model
     * @return
     */
    @RequestMapping(value=ApiUrls.LAYOUT_FOOTER_URL)
    public String layoutFooter(Model model){
        return ApiUrls.LAYOUT_FOOTER_JSP;
    }

    /**
     * 返回布局的left界面
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value=ApiUrls.LAYOUT_LEFT_URL)
    public String layoutLeft(Model model,HttpSession httpSession){
        int isAdmin = ((Employee)httpSession.getAttribute("employee")).getIsAdmin();
        //普通用户界面
        if(isAdmin == 1){
            return ApiUrls.LAYOUT_LEFT_USER_JSP;
        }else{//管理员界面
            return ApiUrls.LAYOUT_LEFT_JSP;
        }
    }

    /**
     * 返回布局center
     * @param httpSession
     * @return
     */
    @RequestMapping(value=ApiUrls.LAYOUT_CENTER_URL)
    public String layoutCenter(Model model,HttpSession httpSession){
        model.addAttribute("employee",(Employee)httpSession.getAttribute("employee"));
        return ApiUrls.LAYOUT_CENTER_JSP;
    }
}

