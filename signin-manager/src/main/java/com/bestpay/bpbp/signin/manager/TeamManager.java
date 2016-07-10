package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.mapper.PlatformMapper;
import com.bestpay.bpbp.signin.dal.mapper.TeamMapper;
import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.dal.models.Team;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * TeamManager
 * Author linxing
 * Version 1.0
 * Date 2016年5月20日
 */
@Slf4j
@Component
public class TeamManager {

    @Autowired
    public TeamMapper teamMapper;

    /**
     * 新增小组表记录
     * @param team
     * @return
     */
    public int insertTeamInfo(Team team) throws Exception {
        return teamMapper.insertTeamInfo(team);
    }

    /**
     * 删除小组信息
     * @param team
     * @throws Exception
     */
    public void deleteTeamInfoByPrimaryKey(Team team) throws Exception {
        teamMapper.deleteTeamInfoByPrimaryKey(team);
    }


    /**
     * 查询所有小组
     * @param team
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<List<Team>> selectAllTeamInfo(Team team,Pageable pageable) throws Exception{
        Map map = BeanUtils.describe(team);
        map.put("pageNo", pageable.getPageNo()*pageable.getRowNum());
        map.put("rowNum",pageable.getRowNum());

        return  new Page(teamMapper.selectAllTeamInfo(map),teamMapper.selectAllTeamCount(team), pageable);
    }

    /**
     * 更新小组信息
     * @param team
     * @return
     * @throws Exception
     */
    public int updateTeamInfoByPrimaryKey(Team team) throws Exception{
        return teamMapper.updateTeamInfoByPrimaryKey(team);
    }

}
