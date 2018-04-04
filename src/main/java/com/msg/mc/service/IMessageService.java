package com.msg.mc.service;

import com.msg.mc.vo.Message;

/**
 * Created by cloudy on 17/6/1.
 */
public interface IMessageService {
    public void processMessage(Message message) throws Exception;
}
