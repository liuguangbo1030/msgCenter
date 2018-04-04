package com.msg.mc.mapper.qbzz;

import com.msg.mc.model.qbzz.Tenders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cloudy ［Cloudy.liu@qibaozz.com］
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 17/6/17 上午11:09
 */
@Mapper
public interface TendersMapper {
    public Tenders findTendersById(@Param("id") Integer id);
}
