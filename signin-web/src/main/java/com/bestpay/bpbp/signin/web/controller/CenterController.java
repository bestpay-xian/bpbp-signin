package com.bestpay.bpbp.signin.web.controller;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Center;
import com.bestpay.bpbp.signin.service.signservice.CenterService;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * CenterController
 * Author 赵星
 * Version 1.0
 * Date 2016年5月20日10:20:59
 */
@Slf4j
@Controller
@RequestMapping(value = ApiUrls.CENTER_URL)
public class CenterController {

    /**
     * center模块CenterController层注入CenterService依赖
     */
    @Autowired
    private CenterService centerService;


    /**
     * 新增中心记录
     * @param center
     * @return
     */
    @RequestMapping(value = ApiUrls.ADD_CENTER_URL,method = RequestMethod.POST)
    public ModelAndView insertCenter(Center center,ModelAndView modelAndView){
        log.info("中心新增-请求参数{}",center);
        try {
            centerService.insertCenter(center);
            log.info("中心新增成功");
            modelAndView.addObject("msg", "添加成功");
            //redirect到查询请求
            modelAndView.setViewName("redirect:" + ApiUrls.CENTER_URL + "" + ApiUrls.SELECT_CENTER_DIRECTIVE_URL+".do");
            return modelAndView;
        } catch (Exception e) {
            log.error("中心新增Exception{}",e);
            modelAndView.addObject("msg", "添加失败");
            return modelAndView;
        }
    }


    /**
     * 跳转新增中心界面
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.ADD_CENTER_DIRECTIVE_URL,method = RequestMethod.GET)
    public String saveCenterUrl(){
        log.info("中心模块-跳转新增界面{}");
        try {
            log.error("中心模块-跳转新增成功");
            return ApiUrls.ADD_CENTER_BACK_URL;
        } catch (Exception e) {
            log.error("中心模块-跳转新增界面exception{}", e);
            return ApiUrls.ADD_CENTER_BACK_URL;
        }
    }

    /**
     * 跳转查询中心界面
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.SELECT_CENTER_DIRECTIVE_URL,method = RequestMethod.GET)
    public String selectCenter(Center center,Pageable pageable, Model model){
        log.info("中心模块-跳转查询界面{}",center);
        try {
            model.addAttribute("list", centerService.selectCenterPageList(center,pageable));
            return ApiUrls.SAVE_OR_UPDATE_CENTER_BACK_URL;
        } catch (Exception e) {
            log.error("中心模块-跳转查询界面exception{}", e);
            return ApiUrls.SAVE_OR_UPDATE_CENTER_BACK_URL;
        }
    }



    /**
     * 按条件查询中心表记录(不分页)
     * @param center
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.SELECT_CENTER_URL,method = RequestMethod.POST)
    public Message selectCenterList(Center center){
        log.info("按条件查询中心(不分页)-请求参数{}",center);
        try {
            List<Center> centerList = centerService.selectCenterList(center);
            log.info("按条件查询中心成功{}", centerList);
            return Message.successRst(centerList, "查询成功");
        } catch (Exception e) {
            log.error("按条件查询中心Exception{}", e);
            return Message.errorRst(null,"查询失败");
        }
    }


    /**
     * 按条件查询中心表记录(分页)
     * @param center
     * @param pageable
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.SELECT_CENTER_PAGE_URL,method = RequestMethod.POST)
    public Message selectCenterPageList(Center center,Pageable pageable){
        log.info("按条件查询中心(分页)-请求参数{}",center);
        try {
            Page<List<Center>> page = centerService.selectCenterPageList(center, pageable);
            log.info("按条件查询中心(分页)结果", page);
            return Message.successRst(page, "查询成功");
        } catch (Exception e) {
            log.error("按条件查询中心(分页)exception{}", e.getMessage());
            return Message.errorRst(null,"查询失败");
        }
    }


    /**
     * 更新中心表记录
     * @param center
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_CENTER_URL,method = RequestMethod.POST)
    public ModelAndView updateCenter(Center center,ModelAndView modelAndView){
        log.info("中心更新-请求参数{}",center);
        try {
            centerService.updateCenter(center);
            log.info("中心更新成功");
            modelAndView.addObject("msg","更新操作成功");
            //redirect到查询请求
            modelAndView.setViewName("redirect:" + ApiUrls.CENTER_URL + "" + ApiUrls.SELECT_CENTER_DIRECTIVE_URL+".do");
            return modelAndView;
        } catch (Exception e) {
            log.error("中心更新exception{}", e);
            modelAndView.addObject("msg", "更新操作失败");
            return modelAndView;
        }
    }


    /**
     * 跳转更新中心界面
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_CENTER_DIRECTIVE_URL,method = RequestMethod.POST)
    public String updateCenterUrl(Center center,Model model){
        log.info("中心模块-跳转更新界面,请求参数{}",center);
        try {
            Center center1 = centerService.selectCenterById(center);
            model.addAttribute("center",center1);
            return ApiUrls.UPDATE_CENTER_BACK_URL;
        } catch (Exception e) {
            log.error("中心模块-跳转更新界面exception{}", e);
            return ApiUrls.UPDATE_CENTER_BACK_URL;
        }
    }



    /**
     * 根据主键删除中心表记录
     * @param center
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.DELETE_CENTER_URL,method = RequestMethod.POST)
    public Message deleteCenter(Center center){
        log.info("中心删除-请求参数{},{}",center);
        try {
           String deleteInfo=centerService.deleteCenter(center);
            return Message.successRst(center,deleteInfo);
        } catch (Exception e) {
            log.error("中心删除exception{}", e);
            return Message.errorRst(center,"删除操作失败");
        }
    }


    /**
     * 根据主键查询中心表记录
     * @param center
     */
    @RequestMapping(value = ApiUrls.FIND_CENTER_BYID_URL,method = RequestMethod.POST)
    public String selectCenterById(Center center,Model model){
        log.info("按主键查询中心-请求参数{}",center);
        try {
            Center c = centerService.selectCenterById(center);
            model.addAttribute("center",c);
            log.info("按主键查询中心成功");
            return ApiUrls.UPDATE_CENTER_BACK_URL;
        } catch (Exception e) {
            log.error("按主键查询中心exception{}", e);
            return ApiUrls.UPDATE_CENTER_BACK_URL;
        }
    }


//    /**
//     * 验证平台名是否有重复记录
//     * @param
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = ApiUrls.VALIDATE_CENTERNAME_ISEXIST_URL,method = RequestMethod.POST)
//    public boolean validateCenterIsExist(Center center){
//        try {
//            log.info("验证平台名-请求参数{}",center);
//            boolean isExist = true;//中心表名不存在
//            List<Center> centerListList = centerService.selectCenterList(center);
//            if(centerListList != null && centerListList.size() > 0){
//                isExist = false;//中心表名存在
//            }
//            log.info("验证平台名(true：中心表名不存在，false:中心表名存在){}",isExist);
//            return isExist;
//        } catch (Exception e) {
//            log.error("验证平台名exception{}", e);
//            return false;
//        }
//    }


    /**
     * 验证平台名是否有重复记录
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.VALIDATE_CENTERNAME_ISEXIST_URL,method = RequestMethod.POST)
    public String validateCenter(Center center){
        try {
            log.info("验证平台名-请求参数{}",center);
            return centerService.validateCenter(center);
        } catch (Exception e) {
            log.error("验证平台名exception{}", e);
            return "Exception";
        }
    }

}
