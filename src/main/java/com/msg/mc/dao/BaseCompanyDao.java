package com.msg.mc.dao;

import com.msg.mc.mapper.qbzz.BaseCompanyMapper;
import com.msg.mc.model.qbzz.BaseCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class BaseCompanyDao {
    @Autowired
    private BaseCompanyMapper baseCompanyMapper;

    public BaseCompany findBaseCompanyById(Integer id) {
        return baseCompanyMapper.findBaseCompanyById(id);
    }
}
