package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.dal.models.Team;
import com.bestpay.bpbp.signin.manager.PlatformManager;
import com.bestpay.bpbp.signin.manager.TeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TeamService
 * Author linxing
 * Version 1.0
 * Date 2016年5月23日
 */
@Service
public class TeamService {
    @Autowired
    private TeamManager teamManager;


    /**
     * 新增小组表记录
     *
     * @param team
     * @return
     */
    public int insertTeamInfo(Team team) throws Exception {
        return teamManager.insertTeamInfo(team);
    }

    /**
     * 删除小组信息
     *
     * @param team
     * @throws Exception
     */
    public void deleteTeamInfoByPrimaryKey(Team team) throws Exception {
        teamManager.deleteTeamInfoByPrimaryKey(team);
    }


    /**
     * 查看小组信息
     *
     * @param team
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<List<Team>> selectTeamInfoList(Team team, Pageable pageable) throws Exception {
        return teamManager.selectAllTeamInfo(team, pageable);
    }

    /**
     * 更新小组信息
     *
     * @param team
     * @return
     * @throws Exception
     */
    public int updateTeamInfo(Team team) throws Exception {
        return teamManager.updateTeamInfoByPrimaryKey(team);
    }

    public String validateTeam(Team team) throws Exception{
               Long count=teamManager.validateTeam(team);
        if(count>0){
            return "HASTEAM";
        }
        return "NOTHASTAM";
    }
}
