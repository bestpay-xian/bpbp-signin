package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Dept;
import com.bestpay.bpbp.signin.manager.DeptManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * DeptService
 * Author liyang
 * Version 1.0
 * Date 2016年5月23日09:56:24
 */
@Slf4j
@Service
public class DeptService {

    /**
     * 注入部门管理manager
     */
    @Autowired
    private DeptManager deptManager;

    /**
     * 查询部门信息，分页
     * @param dept
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<Dept> findDeptList(Dept dept, Pageable pageable) throws Exception{
        //将Dept转Map
        Map map = BeanUtils.describe(dept);
        map.put("pageNo", pageable.getPageNo()*pageable.getRowNum());
        map.put("rowNum",pageable.getRowNum());
        return new Page(deptManager.selectDeptPageList(map), deptManager.selectDeptAllCount(dept), pageable);
    }

    /**
     * 保存部门信息
     * @param dept
     * @return
     * @throws Exception
     */
    public int insertDept(Dept dept) throws Exception{
        return deptManager.insertDept(dept);
    }

    /**
     * 查找部门信息修改
     * @param dept
     * @return
     * @throws Exception
     */
    public Dept findDept(Dept dept) throws Exception{
        return deptManager.selectAllDept(dept).get(0);
    }

    /**
     * 修改部门信息
     * @param dept
     * @throws Exception
     */
    public void updateDept(Dept dept) throws Exception{
        deptManager.updateDept(dept);
    }

    /**
     * 删除部门信息(is_delete=2)
     * @param dept
     * @throws Exception
     */
    public void deleteDept(int dept) throws Exception{
        deptManager.deleteDept(dept);
    }

    /**
     * 查找信息是否存在
     * @param dept
     * @return
     * @throws Exception
     */
    public boolean checkDeptList(Dept dept) throws Exception{
        int deptSize = deptManager.checkDeptList(dept).size();
        if(deptSize >0){
            return false;
        }else{
            return true;
        }
    }
}
