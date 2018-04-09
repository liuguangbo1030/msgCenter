package com.msg.mc.mapper.mc;

import com.msg.mc.model.mc.McSendFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/13 下午6:43
 */
@Mapper
public interface McSendFilterMapper {
    McSendFilter findMcSendFilterByToObject(@Param("toObject") String toObject);
}
