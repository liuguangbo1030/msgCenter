package com.msg.mc.mapper.mc;

import com.msg.mc.model.mc.McSendFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cloudy ［Cloudy.liu@qibaozz.com］
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 17/6/13 下午6:43
 */
@Mapper
public interface McSendFilterMapper {
    McSendFilter findMcSendFilterByToObject(@Param("toObject") String toObject);
}
