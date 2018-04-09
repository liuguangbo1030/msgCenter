package com.msg.mc.mapper.qbzz;

import com.msg.mc.model.qbzz.Tenders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/17 上午11:09
 */
@Mapper
public interface TendersMapper {
    public Tenders findTendersById(@Param("id") Integer id);
}
