package com.msg.mc.dao;

import com.msg.mc.mapper.mc.McSendRecordMapper;
import com.msg.mc.model.mc.McSendRecord;
import com.msg.mc.util.InformationCenterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 下午5:10
 */
@Component
public class McSendMessageDao {
    @Autowired
    private McSendRecordMapper mcSendRecordMapper;

    public void insertMcSendRecord(McSendRecord mcSendRecord) {
        mcSendRecordMapper.insertMcSendRecord(mcSendRecord);
    }

    public McSendRecord findMcSendRecordByToObject(String toObject) {
        if(InformationCenterUtil.isStrEmpty(toObject))
            return null;
        return mcSendRecordMapper.findMcSendRecordByToObject(toObject);
    }

    public Integer countMcSendRecord(Map<String, Object> filter) {
        if(filter == null)
            return 0;
        return mcSendRecordMapper.countMcSendRecord(filter);
    }
}
