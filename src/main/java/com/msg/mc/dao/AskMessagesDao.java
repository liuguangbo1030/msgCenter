package com.msg.mc.dao;

import com.msg.mc.mapper.qbww.AskMessagesMapper;
import com.msg.mc.model.qbww.AskMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class AskMessagesDao {

    @Autowired
    private AskMessagesMapper askMessagesMapper;

    public void insertAskMessages(AskMessages askMessages) {
        askMessagesMapper.insertAskMessages(askMessages);
    }

}
