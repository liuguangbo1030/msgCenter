package com.msg.mc.mapper.qbzz;

import com.msg.mc.model.qbzz.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cloudy on 17/6/2.
 */
@Mapper
public interface UsersMapper {
    public Users findUsersById(@Param("id") Integer id);
}
