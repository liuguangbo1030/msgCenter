package com.msg.mc.thread;

import com.msg.mc.context.ServiceUtil;
import com.msg.mc.service.IMessageService;
import com.msg.mc.vo.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 2016/9/5
 */
@Component("sendTask")
@Scope(value = "prototype")
public class SendMessageTaskThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(SendMessageTaskThread.class);

    private Message message;

    /**
     * 企保招招平台
     */
    public final int PLATFORM_QBZZ = 1;

    /**
     * 企保招招用户中心
     */
    public final int PLATFORM_QBZZ_USER = 2;

    /**
     * 官网
     */
    public final int PLATFORM_WWW = 3;

    /**
     * 企保大师
     */
    public final int PLATFORM_QBDS = 4;

    /**
     * 企保大师用户中心
     */
    public final int PLATFORM_QBDS_USER = 5;

    public SendMessageTaskThread(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        IMessageService messageService = null;
        try {
            switch (message.getPlatformId()) {
                case PLATFORM_QBZZ:
                    messageService = (IMessageService) ServiceUtil.getBean("qbzzMessageService");
                    messageService.processMessage(message);
                    break;
                case PLATFORM_QBZZ_USER:
                    messageService = (IMessageService) ServiceUtil.getBean("qbzzUserMessageService");
                    messageService.processMessage(message);
                    break;
                case PLATFORM_QBDS:
                    messageService = (IMessageService) ServiceUtil.getBean("agentMessageService");
                    messageService.processMessage(message);
                    break;
                case PLATFORM_QBDS_USER:
                    messageService = (IMessageService) ServiceUtil.getBean("agentUserMessageService");
                    messageService.processMessage(message);
                    break;
                default:
                    messageService = (IMessageService) ServiceUtil.getBean("qbzzMessageService");
                    messageService.processMessage(message);
                    break;
            }
        } catch (Exception e) {
            logger.error("消息中心线程处理失败！", e);
        }
    }
}
