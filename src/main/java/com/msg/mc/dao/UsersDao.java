package com.msg.mc.dao;

import com.msg.mc.model.qbzz.Users;
import com.msg.mc.mapper.qbzz.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class UsersDao {
    @Autowired
    private UsersMapper usersMapper;

    public Users findUsersById(Integer id) {
        return usersMapper.findUsersById(id);
    }
}
