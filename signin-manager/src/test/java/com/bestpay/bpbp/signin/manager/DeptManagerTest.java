package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.base.BaseSpringTest;
import com.bestpay.bpbp.signin.dal.mapper.DeptMapper;
import com.bestpay.bpbp.signin.dal.models.Dept;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
public class DeptManagerTest extends BaseSpringTest {

    @Autowired
    public DeptManager deptManager;

    /**
     * 新增部门记录
     * @return
     */
    @Test
    public void insertDept(){
        Dept dept  = new Dept();
        dept.setDeptName("部门测试");
        int id = deptManager.insertDept(dept);
    }

    /**
     * 根据查询所有匹配的部分记录(不分页)
     * @return
     */
    @Test
    public void selectAllDept(){
        Dept dept  = new Dept();
        dept.setDeptName("测试");
        deptManager.selectAllDept(dept);
    }

    /**
     * 根据姓名模糊查询所有匹配的部门记录(分页)
     * @return
     */
    @Test
    public void selectDeptPageList() throws Exception{
        Map map = new HashMap();
        map.put("deptName","测试");
        map.put("pageNo", 0);
        map.put("rowNum",5);
        deptManager.selectDeptPageList(map);
    }


    /**
     * 查询数据总条数
     * @return
     */
    @Test
    public void selectDeptAllCount(){
        Dept dept  = new Dept();
        dept.setDeptName("测试");
        deptManager.selectDeptAllCount(dept);
    }

    /**
     * 根据主键更新部门表
     * @return
     */
    @Test
    public void updateDept(){
        Dept dept  = new Dept();
        dept.setDeptName("测试");
        dept.setDeptId(1);
        deptManager.updateDept(dept);
    }

    /**
     * 根据主键删除平台表对应记录
     */
    @Test
    public void deleteDept(){
        try {
            Dept dept=new Dept();
            dept.setDeptId(1);
            deptManager.deleteDept(dept);
            log.info("删除部门记录成功");
        } catch (Exception e) {
            log.info("删除部门记录失败:", e);
        }
    }
    /**
     * 根据主键删除平台表对应记录
     */
    @Test
    public void checkDeptList(){
        Dept dept  = new Dept();
        dept.setDeptName("测试");
        deptManager.checkDeptList(dept);
    }
}
