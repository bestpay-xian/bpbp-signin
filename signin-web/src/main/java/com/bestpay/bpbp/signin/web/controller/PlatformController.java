package com.bestpay.bpbp.signin.web.controller;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.service.signservice.PlatformService;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * PlatformController
 * Author 赵星
 * Version 1.0
 * Date 2016年5月20日10:20:59
 */
@Slf4j
@Controller
@RequestMapping(value = ApiUrls.PLATFORM_URL)
public class PlatformController {

    /**
     * platform模块PlatformController层注入PlatformService依赖
     */
    @Autowired
    private PlatformService platformService;


    /**
     * 新增平台表记录
     * @param record
     * @return
     */
    @RequestMapping(value = ApiUrls.ADD_PLATFORM_URL,method = RequestMethod.POST)
    public String insertPlatform(Platform record,Model model){
        log.info("平台模块-新增平台，请求参数{}",record);
        try {
            platformService.insertPlatform(record);
            log.info("平台模块-新增平台成功");
            model.addAttribute("msg","业务添加成功");
            return ApiUrls.SAVE_OR_UPDATE_PLATFORM_BACK_URL;
        } catch (Exception e) {
            log.error("平台模块-新增平台exception{}", e);
            model.addAttribute("msg","业务添加失败");
            return ApiUrls.SAVE_OR_UPDATE_PLATFORM_BACK_URL;
        }
    }

    /**
     * 跳转新增用户界面
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.ADD_PLATFORM_DIRECTIVE_URL,method = RequestMethod.GET)
    public String savePlatform(){
        log.info("平台模块-跳转新增界面{}");
        try {
            return ApiUrls.ADD_PLATFORM_BACK_URL;
        } catch (Exception e) {
            log.error("平台模块-跳转新增界面exception{}", e);
            return ApiUrls.ADD_PLATFORM_BACK_URL;
        }
    }

    /**
     * 跳转查询用户界面
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.SELECT_PLATFORM_DIRECTIVE_URL,method = RequestMethod.GET)
    public String selectPlatform(){
        log.info("平台模块-跳转查询界面{}");
        try {
            return ApiUrls.SAVE_OR_UPDATE_PLATFORM_BACK_URL;
        } catch (Exception e) {
            log.error("平台模块-跳转查询界面exception{}", e);
            return ApiUrls.SAVE_OR_UPDATE_PLATFORM_BACK_URL;
        }
    }






    /**
     * 按条件查询平台记录（不分页）
     * @param platform
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.SELECT_PLATFORMLIST_URL,method = RequestMethod.POST)
    public Message selectPlatformList(Platform platform){
        log.info("平台模块-按条件查询平台(不分页)，请求参数{}",platform);
        try {
            List<Platform> pageList = platformService.selectAllPlatformByName(platform);
            log.info("平台模块-按条件查询平台(不分页)结果{}", pageList);
            return Message.successRst(pageList, "查询操作成功");
        } catch (Exception e) {
            log.error("平台模块-按条件查询平台(不分页)exception{}", e);
            return Message.errorRst(null,"查询操作失败");
        }
    }

    /**
     * 按条件查询平台记录（分页）
     * @param platform
     * @param pageable
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.SELECT_PLATFORM_URL,method = RequestMethod.POST)
    public Message selectPlatformByName(Platform platform,Pageable pageable){
        log.info("平台模块-按条件查询平台（分页）请求参数{}",platform,pageable);
        try {
            Page<List<Platform>> page = platformService.selectPlatformPageListByName(platform,pageable);
            log.info("平台模块-按条件查询平台（分页）结果{}", page);
            return Message.successRst(page, "查询成功");
        } catch (Exception e) {
            log.error("平台模块-按条件查询平台（分页）exception", e);
            return Message.errorRst(null,"查询操作失败");
        }
    }

    /**
     * 获取所有平台名
     * @param
     * @return
     */
    @RequestMapping(value = ApiUrls.SELECT_PLATFORMNAME_URL,method = RequestMethod.POST)
    public Message selectAllPlatform(){
        try {
            List<String> platformNameList = platformService.selectAllPlatformName();
            log.info("平台模块-获取平台名结果{}",platformNameList);
            return Message.successRst(platformNameList,"查询成功");
        } catch (Exception e) {
            log.error("平台模块-获取平台名exception{}", e);
            return Message.errorRst(null,"查询操作失败");
        }
    }


    /**
     * 根据主键更新平台表
     * @param record
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_PLATFORM_URL,method = RequestMethod.POST)
    public String updatePlatformByPrimaryKey(Platform record,Model model){
        log.info("平台模块-更新平台，请求参数{}",record);
        try {
            platformService.updatePlatformByPrimaryKey(record);
            log.info("平台模块-更新平台成功",record);
            model.addAttribute("msg","更新操作成功");
            return ApiUrls.SAVE_OR_UPDATE_PLATFORM_BACK_URL;
        } catch (Exception e) {
            log.error("平台模块-更新平台exception{}", e);
            model.addAttribute("msg","更新操作失败");
            return ApiUrls.SAVE_OR_UPDATE_PLATFORM_BACK_URL;
        }
    }

    /**
     * 根据主键删除平台表对应记录
     * @param record
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.DELETE_PLATFORM_URL,method = RequestMethod.POST)
    public Message deletePlatformByPrimaryKey(Platform record){
        log.info("平台模块-删除平台，请求参数{}",record);
        try {
            platformService.deletePlatformByPrimaryKey(record);
            log.info("平台模块-删除平台成功{}",record);
            return Message.successRst(record,"删除操作成功");
        } catch (Exception e) {
            log.error("平台模块-删除平台exception{}", e);
            return Message.errorRst(record,"删除操作失败");
        }
    }



    /**
     * 根据主键查询平台表对应记录
     * @param record
     */
    @RequestMapping(value = ApiUrls.FIND_PLATFORM_BYID_URL,method = RequestMethod.POST)
    public String selectPlatformById(Platform record,Model model){
        log.info("平台模块-主键查询平台,请求参数{}",record);
        try {
            Platform platform = platformService.selectPlatformById(record);
            model.addAttribute("record",platform);
            log.info("平台模块-主键查询平台结果{}",platform);
            return ApiUrls.UPDATE_PLATFORM_BACK_URL;
        } catch (Exception e) {
            log.error("平台模块-主键查询平台exception{}{}", e);
            return ApiUrls.UPDATE_PLATFORM_BACK_URL;
        }
    }


    /**
     * 验证平台名是否有重复记录
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.VALIDATE_PLATFORMNAME_ISEXIST_URL,method = RequestMethod.POST)
    public boolean validatePlatformNameIsExist(Platform platform){
        try {
            boolean isExist = true;//厂商名不存在
            List<Platform> platformNameList = platformService.selectAllPlatformByName(platform);
            if(platformNameList != null && platformNameList.size() > 0){
                isExist = false;//厂商名存在
            }
            log.info("平台模块-验证平台名是否重复（true：平台名不存在，false：平台名已存在）",isExist);
            return isExist;
        } catch (Exception e) {
            log.error("平台模块-验证平台名是否重复exception{}", e);
            return false;
        }
    }



}
