package com.msg.mc.dao;

import com.msg.mc.mapper.mc.McSendFilterMapper;
import com.msg.mc.model.mc.McSendFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/13 下午7:03
 */
@Component
public class McSendFilterDao {
    @Autowired
    private McSendFilterMapper mcSendFilterMapper;

    public McSendFilter findMcSendFilterByToObject(String toObject) {
        return mcSendFilterMapper.findMcSendFilterByToObject(toObject);
    }
}
