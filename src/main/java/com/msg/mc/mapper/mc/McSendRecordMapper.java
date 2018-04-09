package com.msg.mc.mapper.mc;

import com.msg.mc.model.mc.McSendRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 下午5:02
 */
@Mapper
public interface McSendRecordMapper {

    void insertMcSendRecord(McSendRecord mcSendRecord);

    McSendRecord findMcSendRecordByToObject(@Param("toObject") String toObject);

    Integer countMcSendRecord(Map<String, Object> filter);
}
