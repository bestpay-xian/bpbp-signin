/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.manager.commonManager;

import com.alibaba.fastjson.JSON;
import com.bestpay.bpbp.signin.common.LaunchServiceUtils;
import com.bestpay.bpbp.signin.common.PageParameter;
import com.bestpay.bpbp.signin.common.constant.Constant;
import com.bestpay.bpbp.signin.manager.dto.common.CommonRequest;

import java.util.Map;

/**
 * 公共Manager方法
 *@author  zhaoss
 * @param <REQ>
 */
public abstract class BaseConfigManager<REQ extends CommonRequest> {

    /**
     * 公用将分页查询条件封装到map
     *
     * @param queryReq 查询条件
     * @return 带分页查询条件
     */
    protected Map<String, Object> getQueryMap(REQ queryReq) {
        Map<String, Object> queryMap = JSON.parseObject(JSON.toJSONString(queryReq));
        PageParameter page = LaunchServiceUtils.getPageParameter(queryReq.getCurrentPage(),
                queryReq.getPageSize());
        queryMap.put(Constant.PAGE, page);
        return queryMap;
    }

}
