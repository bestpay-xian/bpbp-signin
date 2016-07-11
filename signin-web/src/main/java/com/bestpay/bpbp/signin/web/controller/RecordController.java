/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.web.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.bestpay.bpbp.signin.dal.models.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.common.utils.DateUtil;
import com.bestpay.bpbp.signin.dal.models.Employee;
import com.bestpay.bpbp.signin.dal.models.Record;
import com.bestpay.bpbp.signin.dal.models.RecordRequest;
import com.bestpay.bpbp.signin.service.signservice.PlatformService;
import com.bestpay.bpbp.signin.service.signservice.RecordService;
import com.bestpay.bpbp.signin.web.constants.ApiUrls;

import lombok.extern.slf4j.Slf4j;

/**
 * RecordController
 *
 * @author linxing
 */
@Slf4j
@Controller
public class RecordController {

    @Autowired
    private RecordService   recordService;

    @Autowired
    private PlatformService platformService;

    /**
     * 查询员工打卡记录
     *
     * @param record      签到信息
     * @param HttpSession Session
     * @param startDate   查询条件
     * @param endDate     查询条件
     * @param pageable    分页信息
     * @return Message
     */
    @RequestMapping(value = ApiUrls.QUERY_RECORD_URL, method = RequestMethod.POST)
    @ResponseBody
    private Message selectRecordList(RecordRequest record, HttpSession HttpSession,
                                     String startDate, String endDate, Pageable pageable) {
        log.info("签到信息查询入参{}{}{}{}{}", record, HttpSession, startDate, endDate, pageable);
        try {
            //前台传字符串
            if (null != startDate && !"".equals(startDate)) {
                record.setStartTime(DateUtil.parse(startDate, DateUtil.settlePattern));
            }
            if (null != endDate && !"".equals(endDate)) {
                record.setEndTime(DateUtil.parse(endDate, DateUtil.settlePattern));
            }

            Employee employee = (Employee) HttpSession.getAttribute("employee");
            record.setEmployeeId(employee.getEmployeeId());
            record.setIsAdminFlag(employee.getIsAdmin());
            Page<List<Record>> recordList = recordService.selectRecordList(record, pageable);
            return Message.successRst(recordList, "查询成功");
        } catch (ParseException e) {
            log.error("查询员工时间转化失败{}", e);
            return Message.errorRst(null, "查询成功");
        } catch (Exception e) {
            log.error("查询员工签到信息失败{}", e);
            return Message.errorRst(null, "查询成功");
        }
    }

    /**
     * 员工签到
     *
     * @param record      签到信息
     * @param HttpSession Session
     * @param model       model
     * @return 跳转页面
     */
    // @Token(remove = true)
    @RequestMapping(value = ApiUrls.INSERT_RECORD_URL, method = RequestMethod.POST)
    private String insertRecordInfo(Record record, HttpSession HttpSession, Model model) {
        log.info("员工签到入参{}{}", record, HttpSession);
        Employee employee = (Employee) HttpSession.getAttribute("employee");
        record.setEmployeeId(employee.getEmployeeId());
        try {
            recordService.insertStartRecordInfo(record);
            model.addAttribute("message", "签到成功");
            return "redirect:/" + ApiUrls.RECORD_SIGNIN__FROM_URL + ".do";
        } catch (Exception e) {
            log.error("用户签到失败{}", e);
            model.addAttribute("message", "插入失败请重试");
            return ApiUrls.RECORD_SIGNIN_URL;
        }
    }

    /**
     * 删除签到信息目前没有使用
     *
     * @param record
     * @return
     */
    @RequestMapping(value = ApiUrls.DELETE_RECORD_URL, method = RequestMethod.POST)
    @ResponseBody
    private Message deleteRecordByEmployeeId(Record record) {
        log.info("入参{}", record);
        return recordService.deleteRecordByEmployeeId(record);
    }

    /**
     * 更新签到信息目前没有使用
     *
     * @param record
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_RECORD_URL, method = RequestMethod.POST)
    @ResponseBody
    private Message updateRecordByEmployeeId(Record record) {
        log.info("入参{}", record);
        return recordService.updateRecordByEmployeeId(record);
    }

    /**
     * 查询签到记录 from
     * @param recordRequest
     * @param HttpSession
     * @param pageable
     * @param model
     * @return
     */
    // @Token(save = true)
    @RequestMapping(value = ApiUrls.RECORD_SIGNIN__FROM_URL)
    private String findRecordList(RecordRequest recordRequest, HttpSession HttpSession,
                                  Pageable pageable, Model model) {
        log.info("签到信息查询入参:{}", recordRequest);
        try {
            Employee employee = (Employee) HttpSession.getAttribute("employee");
            recordRequest.setEmployeeId(employee.getEmployeeId());
            recordRequest.setIsAdminFlag(employee.getIsAdmin());
            model.addAttribute("list", recordService.selectRecordList(recordRequest, pageable));
            return ApiUrls.QUERY_RECORD_JSP;
        } catch (Exception e) {
            log.error("签到信息查询错误信息:{}", e);
            return ApiUrls.QUERY_RECORD_JSP;
        }
    }

    /**
     * 转到签到页面
     * @param model
     * @return
     */
    @RequestMapping(value = ApiUrls.SIGNIN__FROM_URL)
    private String signinFrom(Model model) {
        try {
            List<Platform> platform = platformService.selectAllPlatformName();
            model.addAttribute("platform",platform);
            log.info("转到签到页面结果：{}",platform);
            return ApiUrls.SIGNIN__FROM_URL_JSP;
        } catch (Exception e) {
            log.error("转到签到页面出错：{}",e);
            return ApiUrls.SIGNIN__FROM_URL_JSP;
        }
    }

}
