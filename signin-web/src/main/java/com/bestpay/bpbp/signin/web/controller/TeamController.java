package com.bestpay.bpbp.signin.web.controller;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.dal.models.Team;
import com.bestpay.bpbp.signin.service.signservice.PlatformService;
import com.bestpay.bpbp.signin.service.signservice.TeamService;
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
 * TeamController
 * Author linxing
 * Version 1.0
 * Date 2016年5月23日
 */
@Slf4j
@Controller
public class TeamController {

    /**
     * teamService: 处理小组CRUD
     */
    @Autowired
    private TeamService teamService;

    @RequestMapping(value =ApiUrls.TO_SELECT_URL, method = RequestMethod.GET)
    public String toSelectPage(Team team,Pageable pageable,Model model){
        log.info("请求参数{},{}", "跳转到查询页面", team);
        try{
            log.info("跳转小组查询页面-----跳转成功");
            model.addAttribute("list", teamService.selectTeamInfoList(team,pageable));
            return "user/select_team";
        }catch(Exception e){
            log.error("跳转小组查询页面-----跳转成功失败", e);
            model.addAttribute("msg", "跳转失败");
            return ApiUrls.ADD_TEAM_FAILURE_URL;
        }
    }


    /**
     * 新增小组表记录
     *
     * @param team
     * @return
     */
    @RequestMapping(value = ApiUrls.ADD_TEAM_URL, method = RequestMethod.POST)
    public ModelAndView insertTeamInfo(Team team, ModelAndView modelAndView) {
        log.info("请求参数{},{}", team, modelAndView);
        try {
            teamService.insertTeamInfo(team);
            log.info("插入小组-----业务添加成功");
            modelAndView.addObject("msg", "业务添加成功");
            modelAndView.setViewName("redirect:"+ApiUrls.TO_SELECT_URL+".do");
            return modelAndView;
        } catch (Exception e) {
            log.error("插入小组-----业务添加结果{}", e);
            modelAndView.addObject("msg", "业务添加失败");
            return modelAndView;
        }
    }


    /**
     * 查询所有的小组信息
     *
     * @param team
     * @param pageable
     * @return
     */
    @RequestMapping(value = ApiUrls.LIST_TEAM_URL, method = RequestMethod.POST)
    @ResponseBody
    public Message selectTeamInfoList(Team team, Pageable pageable) {
        log.info("查询所有的小组信息-----请求参数{},{}", team, pageable);
        try {
            Page<List<Team>> teamInfoList = teamService.selectTeamInfoList(team, pageable);
            log.info("查询所有的小组信息-----业务查询结果", teamInfoList);
            return Message.successRst(teamInfoList, "查询成功");
        } catch (Exception e) {
            log.error("查询所有的小组信息失败-----业务查询结果{}", e);
            return Message.errorRst(null, "查询操作失败");
        }
    }

    /**
     * 跳转到update页面
     *
     * @param team
     * @param model
     * @return
     */
    @RequestMapping(value = ApiUrls.SELECT_TEAM_URL, method = RequestMethod.POST)
    public String selectTeamInfoByPrimaryKey(Team team, Model model) {
        log.info("查询小组信息-----请求参数{}", team);
        try {
            Page<List<Team>> teamInfoList = teamService.selectTeamInfoList(team, new Pageable());
            Team updateTeam = null;
            if(null != teamInfoList &&  null != teamInfoList.getLists() && teamInfoList.getLists().size()>0){
                updateTeam  = (Team)teamInfoList.getLists().get(0);
            }
            log.info("查询小组信息-----业务查询结果", updateTeam);
            model.addAttribute(updateTeam);
            return ApiUrls.SELECT_TEAM_SUCCESS_URL;
        } catch (Exception e) {
            log.error("查询小组信息失败-----业务查询结果{}", e);
            return ApiUrls.ADD_TEAM_SUCCESS_URL;
        }
    }


    /**
     * 更新小组信息
     *
     * @param modelAndView
     * @param team
     * @return
     */
    @RequestMapping(value = ApiUrls.UPDATE_TEAM_URL, method = RequestMethod.POST)
    public ModelAndView udpateTeamInfoByPrimaryKey(Team team, ModelAndView modelAndView) {
        log.info("小组业务请求参数{}", team,modelAndView);
        try {
            teamService.updateTeamInfo(team);
            log.info("小组业务添加成功");
            modelAndView.addObject("msg", "小组业务更新成功");
            modelAndView.setViewName("redirect:"+ApiUrls.TO_SELECT_URL+".do");
            return modelAndView;
        } catch (Exception e) {
            log.error("业务添加结果{}", e);
            modelAndView.addObject("msg", "小组业务添加失败");
            return modelAndView;
        }
    }

    /**
     * 删除小组信息
     *
     * @param team
     * @return
     */
    @RequestMapping(value = ApiUrls.DELETE_TEAM_URL, method = RequestMethod.POST)
    @ResponseBody
    public Message deleteTeamInfoByPrimaryKey(Team team) {
        log.info("小组业务请求参数{}", team);
        try {
            teamService.deleteTeamInfoByPrimaryKey(team);
            log.info("小组业务业务删除成功");
            return Message.successRst(null, "小组业务删除成功");
        } catch (Exception e) {
            log.error("小组业务业务添加结果{}", e);
            return Message.successRst(null, "小组业务删除失败");
        }
    }

//    /**
//     * 验证平台名是否有重复记录
//     * @param
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = ApiUrls.VALIDATE_TEAMNAME_ISEXIST_URL,method = RequestMethod.POST)
//    public boolean validatTeamIsExist(Team team){
//        try {
//            log.info("验证小组名-请求参数{}",team);
//            boolean isExist = true;//小组表名不存在
//            Page<List<Team>> pageList = teamService.selectTeamInfoList(team, new Pageable());
//
//            if(pageList.getLists() != null && pageList.getLists().size() > 0){
//                isExist = false;//小组表名存在
//            }
//            log.info("验证小组名(true：小组表名不存在，false:小组表名存在){}",isExist);
//            return isExist;
//        } catch (Exception e) {
//            log.error("验证小组名exception{}", e);
//            return false;
//        }
//    }

    /**
     * 验证平台名是否有重复记录
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = ApiUrls.VALIDATE_TEAMNAME_ISEXIST_URL,method = RequestMethod.POST)
    public String validatTeamIsExist(Team team){
        try {
            log.info("验证小组名-请求参数{}",team);
            String str1=teamService.validateTeam(team);
            return str1;
        } catch (Exception e) {
            log.error("验证小组名exception{}", e);
            return "Exception";
        }
    }

}
