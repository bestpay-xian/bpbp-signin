package com.bestpay.bpbp.signin.service.signservice;

import com.bestpay.bpbp.signin.common.Message;
import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.common.constant.Constant;
import com.bestpay.bpbp.signin.dal.models.Record;
import com.bestpay.bpbp.signin.dal.models.RecordRequest;
import com.bestpay.bpbp.signin.manager.RecordManager;
import com.bestpay.bpbp.signin.manager.dto.common.CommonResponse;
import com.bestpay.bpbp.signin.manager.dto.request.RecordInfoRequest;
import com.bestpay.bpbp.signin.manager.dto.response.RecordResponse;
import com.bestpay.bpbp.signin.service.log.BpbpSigninDigestLogHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RecordService
 * Author linxing
 * Version 1.0
 * Date 2016/5/16 11:20
 */
@Slf4j
@Service
public class RecordService {
    @Autowired
    private RecordManager recordManager;

    /**
     * 查询员工信息
     *
     * @param record
     * @return Message
     */
    public Page<List<Record>> selectRecordList(RecordRequest record, Pageable pageable) throws  Exception {
        log.info("请求参数{}{}", record,pageable);
        return recordManager.selectRecordList(record, pageable);
    }

    /**
     * 查询员工当天是否签到，如果没有就进行签到，如果有就进行签退
     *
     * @param record
     * @return Message
     * @throws Exception
     */
    public int insertStartRecordInfo(Record record) throws  Exception{
            log.info("请求参数{}", record);
            return recordManager.insertStartRecordInfo(record);
    }

    /**
     * 删除签到记录
     *
     * @param record
     * @return
     */
    public Message deleteRecordByEmployeeId(Record record) {
        log.info("请求参数{}", record);
        try {
            recordManager.deleteRecordByEmployeeId(record);
            log.info("业务删除成功");
            return Message.successRst(null, "删除成功");
        } catch (Exception e) {
            log.error("业务删除结果{}", e);
            return Message.errorRst(null, "删除操作失败");
        }
    }

    /**
     * 更新签到记录：主要用于补签到
     *
     * @param record
     * @return
     */
    public Message updateRecordByEmployeeId(Record record) {
        log.info("请求参数{}", record);
        try {
            recordManager.updateRecordByEmployeeId(record);
            log.info("业务更新成功");
            return Message.successRst(null, "更新成功");
        } catch (Exception e) {
            log.error("业务更新结果{}", e);
            return Message.errorRst(null, "更新操作失败");
        }
    }


}
