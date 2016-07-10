package com.bestpay.bpbp.signin.manager;

import com.bestpay.bpbp.signin.common.Page;
import com.bestpay.bpbp.signin.common.Pageable;
import com.bestpay.bpbp.signin.common.utils.DateUtil;
import com.bestpay.bpbp.signin.dal.mapper.RecordMapper;
import com.bestpay.bpbp.signin.dal.models.Record;
import com.bestpay.bpbp.signin.dal.models.RecordRequest;
import com.bestpay.bpbp.signin.manager.commonManager.BaseConfigManager;
import com.bestpay.bpbp.signin.manager.dto.request.RecordInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RecordManager
 * Author linxing
 * Version 1.0
 * Date 2016/5/16 14:40
 */
@Slf4j
@Component
public class RecordManager extends BaseConfigManager<RecordInfoRequest> {

    @Autowired
    private RecordMapper recordMapper;

    public Page<List<Record>> selectRecordList(RecordRequest record, Pageable pageable) throws Exception {
        Map map = BeanUtils.describe(record);
        map.put("pageNo", pageable.getPageNo()*pageable.getRowNum());
        map.put("rowNum", pageable.getRowNum());
        map.put("startTime", record.getStartTime());
        map.put("endTime", record.getEndTime());
        map.put("employeeName", record.getEmployeeName());
        map.put("employee", record.getEmployeeId());
        return new Page(recordMapper.selectRecord(map), recordMapper.selectRecordCount(record), pageable);
    }

    /**
     * 请求查询员工记录信息
     * @param recordInfoRequest 请求信息
     * @throws
     * @throws java.sql.SQLException
     * @throws Exception
     * @return response
     *//*
    public CommonResponse<RecordResponse> selectRecordListInfo(RecordInfoRequest recordInfoRequest) throws Exception {
        List<RecordResponse> listResponse = new ArrayList<RecordResponse>();
        CommonResponse response = new CommonResponse();
        Map<String, Object> queryMap = getQueryMap(recordInfoRequest);
        //获取分页总数返回
        Long pageCount = recordMapper.selectRecordCount(queryMap);
        List<Record> recordList = recordMapper.selectRecordPage(queryMap);
        if (recordList != null && recordList.size() > 0) {
            for (Record rd : recordList) {
                RecordResponse recordResponse = new RecordResponse();
                BeanUtils.copyProperties(rd, recordResponse);
                recordResponse.setPageCount(pageCount);
                listResponse.add(recordResponse);
            }
            response.setResult(listResponse);
            response.setRespCode(SigninErrorCodeEnum.SUCCESS.getErrorcode());
            response.setRespDesc(SigninErrorCodeEnum.SUCCESS.getErrordesc());
            return response;
        } else {
            response.setRespCode(SigninErrorCodeEnum.SUCCESS.getErrorcode());
            response.setRespDesc(SigninErrorCodeEnum.QUERY_DATA_EMPTY.getErrordesc());

        }
        return response;
    }*/

    /**
     * 查询员工当天是否签到，如果没有就进行签到，如果有就进行签退
     *
     * @param record
     * @return
     * @throws Exception
     */
    public int insertStartRecordInfo(Record record) throws Exception {

        Record r = recordMapper.selectRecordByEmployeeIdToday(record);

        if (null != r && null != r.getStartTime()) {

            /**
             * 如果当前用户已经签到过，就取当前时间作为签退时间
             * HoursWork： 如果是两点以后就减去中午休息时间，如果六点以后，就减去全天休息时间
             */
            float totalDate = DateUtil.timeDifference(r.getStartTime(), DateUtil.getCurrentDate());
            r.setEndTime(DateUtil.getCurrentDate());
            //date.getHours(); //这个方法已经不推荐使用了
            int startHour = DateUtil.getHourFromDate(r.getStartTime());
            int endhour = DateUtil.getHourFromDate(null);

            if (startHour < 12 && endhour > 14) {
                if (endhour > 19) {
                    double temp = totalDate - 2.5;
                    totalDate = (float)temp;
                } else {
                    double temp = totalDate - 1.5;
                    totalDate = (float)temp;
                }

            } else if (startHour > 12 && endhour > 19) {
                totalDate = totalDate - 1;
            }

            r.setHoursWork(totalDate);

            return recordMapper.updateEndRecordInfo(r);
        } else {
            return recordMapper.insertStartRecordInfo(record);
        }
    }

    public void deleteRecordByEmployeeId(Record record) throws Exception {
        recordMapper.deleteRecordByEmployeeId(record);
    }

    public int updateRecordByEmployeeId(Record record) throws Exception {
        return recordMapper.updateEndRecordInfo(record);
    }
}
