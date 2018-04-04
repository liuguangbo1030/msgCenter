package com.msg.mc.kafka;

import com.alibaba.fastjson.JSON;
import com.msg.mc.Application;
import com.msg.mc.service.ISendService;
import com.msg.mc.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by cloudy on 17/5/31.
 */
@Component
public class MsgConsumer {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ISendService sendService;

    @KafkaListener(topics = "messageCenter")
    public void processMessage(String content) {
        try {
            if(content == null || content.isEmpty()) {
                throw new Exception("接收到的信息为空");
            }
            logger.info("kafka接收信息" + content);
            Message message = JSON.parseObject(content, Message.class);
            if(message == null) {
                throw new Exception("接收到的信息不正确");
            }
            if(message.getId() == null) {
                throw new Exception("消息id为空");
            }
            if(message.getPlatformId() == null) {
                throw new Exception("平台id为空");
            }
            sendService.sendMessage(message);
        }catch (Exception ex) {
            logger.error("消息中心处理失败！",ex);
        }
    }
}