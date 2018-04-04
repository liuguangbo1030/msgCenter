package com.msg.mc.mapper.qbzz;

import com.msg.mc.model.qbzz.BaseCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cloudy on 17/6/2.
 */
@Mapper
public interface BaseCompanyMapper {
    public BaseCompany findBaseCompanyById(@Param("id") Integer id);
}
