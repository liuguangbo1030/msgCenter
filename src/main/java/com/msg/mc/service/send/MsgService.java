package com.msg.mc.service.send;

import com.msg.mc.model.qbww.AskMessages;
import com.msg.mc.dao.AskMessagesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class MsgService {
    private Logger logger = LoggerFactory.getLogger(MsgService.class);

    @Autowired
    private AskMessagesDao askMessagesDao;

    /**
     * 处理站内信
     * @param toUserId
     * @param content
     */
    public void handle(Integer toUserId, String content) {
        AskMessages messages = new AskMessages();
        messages.setContent(content);
        messages.setFromUserId(1);
        messages.setToUserId(toUserId);
        messages.setIsRead(0);
        messages.setFromDeleted(0);
        messages.setToDeleted(0);
        logger.info("发送站内信：" + messages.toString());
        askMessagesDao.insertAskMessages(messages);
    }
}
