package com.bestpay.bpbp.signin.dal.mapper;


import com.bestpay.bpbp.signin.dal.models.Platform;
import com.bestpay.bpbp.signin.dal.models.Team;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * TeamMapper
 * Author linxing
 * Version 1.0
 * Date 2016年5月23日
 */
public interface TeamMapper {

    /**
     * 新增小组表记录
     * @param team
     * @return
     */
   int insertTeamInfo(Team team) throws SQLException;

    /**
     * 删除小组信息
     * @param team
     * @throws SQLException
     */
   void deleteTeamInfoByPrimaryKey(Team team) throws SQLException;


    /**
     * 查询所有小组信息
     * @param map
     * @return
     */

   List<Team> selectAllTeamInfo(Map map)  throws SQLException;

    /**
     * 查询数据总条数
     * @param team
     * @return
     */
   Long selectAllTeamCount(Team team)  throws SQLException;

    /**
     * 更新小组信息
     * @param team
     * @return
     */
   int updateTeamInfoByPrimaryKey(Team team)  throws SQLException;


}
