package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.mapper.CenterMapper;
import com.bestpay.bpbp.signin.dal.mapper.TeamMapper;
import com.bestpay.bpbp.signin.dal.models.Center;
import com.bestpay.bpbp.signin.dal.models.Team;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * CenterManager
 * Author 赵星
 * Version 1.0
 * Date 2016年5月23日14:42:00
 */
@Slf4j
@Component
public class CenterManager {


    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    public CenterMapper centerMapper;

    /**
     * 新增中心表记录
     * @param center
     * @return
     */
    public void insertCenter(Center center) throws Exception{
        centerMapper.insertCenter(center);
    }

    /**
     * 按条件查询中心表记录(不分页)
     * @param center
     * @return
     */
    public List<Center> selectCenterList(Center center) throws Exception{
        return centerMapper.selectCenterList(center);
    }

    /**
     * 按条件查询中心表记录(分页)
     * @param center
     * @param pageable
     * @return
     */
    public Page<List<Center>> selectCenterPageList(Center center,Pageable pageable) throws Exception{
        Map map = BeanUtils.describe(center);
        map.put("pageNo", pageable.getPageNo()*pageable.getRowNum());
        map.put("rowNum",pageable.getRowNum());
        return new Page(centerMapper.selectCenterPageList(map),centerMapper.selectCenterCount(center),pageable);
    }


    /**
     * 根据主键查询中心表记录
     * @return
     */
    public Center selectCenterById(Center center) throws Exception{
        return centerMapper.selectCenterById(center);
    }


    /**
     * 更新中心表记录
     * @param center
     * @return
     */
    public void updateCenter(Center center) throws Exception{
        centerMapper.updateCenter(center);
    }

    /**
     * 根据主键删除中心表记录
     * @param center
     */
    public String deleteCenter(Center center) throws Exception{
        Team team=new Team();
        team.setCenterId(center.getCenterId());
        List<Team> teamList=teamMapper.selectTeamByCenterId(team);
        String notDeleteInfo="NOT_DELETE";
        String DeleteInfo="CAN_DELETE";
        if(!CollectionUtils.isEmpty(teamList)){
         return notDeleteInfo;
        }
        centerMapper.deleteCenter(center);
        return DeleteInfo;
    }

    public List<Center> validateCenter(Center center) throws Exception{
        return centerMapper.validateCenter(center);
    }
}
