package com.msg.mc.mapper.mc;

import com.msg.mc.model.mc.McSendRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author cloudy ［Cloudy.liu@qibaozz.com］
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 17/6/7 下午5:02
 */
@Mapper
public interface McSendRecordMapper {

    void insertMcSendRecord(McSendRecord mcSendRecord);

    McSendRecord findMcSendRecordByToObject(@Param("toObject") String toObject);

    Integer countMcSendRecord(Map<String, Object> filter);
}
