package com.msg.mc.mapper.qbww;

import com.msg.mc.model.qbww.AskMessages;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by cloudy on 17/5/31.
 */
@Mapper
public interface AskMessagesMapper {
    void insertAskMessages(AskMessages askMessages);
}
