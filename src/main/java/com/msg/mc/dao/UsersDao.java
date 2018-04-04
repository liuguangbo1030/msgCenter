package com.msg.mc.dao;

import com.msg.mc.model.qbzz.Users;
import com.msg.mc.mapper.qbzz.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy ［Cloudy.liu@qibaozz.com］
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
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
