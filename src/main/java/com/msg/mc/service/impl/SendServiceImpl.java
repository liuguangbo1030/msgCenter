package com.msg.mc.service.impl;

import com.msg.mc.service.ISendService;
import com.msg.mc.thread.SendMessageTaskThread;
import com.msg.mc.thread.worker.MessagePool;

import com.msg.mc.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author cloudy
 * @version 1.0
 * @date 2016/8/30.
 */
@Service
public class SendServiceImpl implements ISendService {

    private Logger logger = LoggerFactory.getLogger(SendServiceImpl.class);

    public void sendMessage(Message message) throws Exception {
        try {

            //线程池执行发送短信任务
            MessagePool.instance.addTask(new SendMessageTaskThread(message));

        } catch (Exception e) {

            logger.error("短信发送失败！", e);

            throw e;

        }
    }
}
