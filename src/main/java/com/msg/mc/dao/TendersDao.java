package com.msg.mc.dao;

import com.msg.mc.mapper.qbzz.TendersMapper;
import com.msg.mc.model.qbzz.Tenders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class TendersDao {
    @Autowired
    private TendersMapper tendersMapper;

    public Tenders findTendersById(Integer id){
        return tendersMapper.findTendersById(id);
    }
}
