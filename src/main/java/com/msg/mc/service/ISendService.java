package com.msg.mc.service;


import com.msg.mc.vo.Message;

/**
 * 短信发送接口
 *
 * @author zhouxing
 * @version 1.0
 * @company 深圳市前海鼎浩木城网络有限公司
 * @date 2016年9月5日
 */
public interface ISendService {

    /**
     * 短信发送接口
     *
     * @param message
     * @throws Exception
     */
    public void sendMessage(Message message) throws Exception;
}