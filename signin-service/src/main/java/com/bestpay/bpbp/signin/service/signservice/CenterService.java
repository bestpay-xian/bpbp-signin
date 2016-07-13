package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.dal.models.Center;
import com.bestpay.bpbp.signin.manager.CenterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.print.DocFlavor;
import java.util.List;

/**
 * CenterService
 * Author 赵星
 * Version 1.0
 * Date 2016年5月23日14:55:45
 */
@Service
public class CenterService {
    /**
     * center模块CenterService层注入centerManager依赖
     */
    @Autowired
    private CenterManager centerManager;


    /**
     * 新增中心表记录
     * @param center
     * @return
     */
    public void insertCenter(Center center) throws Exception{
        centerManager.insertCenter(center);
    }

    /**
     * 按条件查询中心表记录(不分页)
     * @param center
     * @return
     */
    public List<Center> selectCenterList(Center center) throws Exception{
        return centerManager.selectCenterList(center);
    }

    /**
     * 按条件查询中心表记录(分页)
     * @param center
     * @param pageable
     * @return
     */
    public Page<List<Center>> selectCenterPageList(Center center,Pageable pageable) throws Exception{
        return centerManager.selectCenterPageList(center, pageable);
    }


    /**
     * 更新中心表记录
     * @param center
     * @return
     */
    public void updateCenter(Center center) throws Exception{
        centerManager.updateCenter(center);
    }

    /**
     * 根据主键删除中心表记录
     * @param center
     */
    public String deleteCenter(Center center) throws Exception{
        String deleteInfo=centerManager.deleteCenter(center);
        return deleteInfo;
    }



    /**
     * 根据主键查询中心表记录
     * @param center
     */
    public Center selectCenterById(Center center) throws Exception{
        return centerManager.selectCenterById(center);
    }


    public String validateCenter(Center center) throws Exception{
        List<Center> centerList=centerManager.validateCenter(center);
        if(CollectionUtils.isEmpty(centerList)){
            return "ISEXSTER";
        }else {
            return "NOTEXSTER";
        }

    }

}
