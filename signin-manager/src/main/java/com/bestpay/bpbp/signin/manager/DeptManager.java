package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.mapper.DeptMapper;
import com.bestpay.bpbp.signin.dal.mapper.PlatformMapper;
import com.bestpay.bpbp.signin.dal.models.Dept;
import com.bestpay.bpbp.signin.dal.models.Platform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * DeptManager
 * Author 李洋
 * Version 1.0
 * Date 2016年5月23日10:18:44
 */
@Slf4j
@Component
public class DeptManager {

    @Autowired
    public DeptMapper deptMapper;

    /**
     * 新增部门记录
     * @param dept
     * @return
     */
    public int insertDept(Dept dept){
        return deptMapper.insertDept(dept);
    }

    /**
     * 根据查询所有匹配的部分记录(不分页)
     * @param dept
     * @return
     */

    public List<Dept> selectAllDept(Dept dept){
        return deptMapper.selectAllDept(dept);
    }

    /**
     * 根据姓名模糊查询所有匹配的部门记录(分页)
     * @param map
     * @return
     */
    public List<Dept> selectDeptPageList(Map map) throws Exception{
        return deptMapper.selectDeptPageList(map);
    }


    /**
     * 查询数据总条数
     * @param dept
     * @return
     */
    public int selectDeptAllCount(Dept dept){
       return deptMapper.selectDeptAllCount(dept);
    }

    /**
     * 根据主键更新部门表
     * @param dept
     * @return
     */
    public void updateDept(Dept dept){
        deptMapper.updateDept(dept);
    }

    /**
     * 根据主键删除平台表对应记录
     * @param dept
     */
    public void deleteDept(int dept){
        deptMapper.deleteDept(dept);
    }
    /**
     * 查询信息是否存在
     * @param dept
     */
    public List<Dept> checkDeptList(Dept dept){
        return deptMapper.checkDept(dept);
    }
}
