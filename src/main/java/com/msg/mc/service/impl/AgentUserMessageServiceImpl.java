package com.msg.mc.service.impl;

import com.msg.mc.service.send.EmailService;
import com.msg.mc.service.IMessageService;
import com.msg.mc.service.send.SmsService;
import com.msg.mc.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  cloudy
 * @version  1.0
 * @date 17/6/7 上午10:08
 */
@Component("agentUserMessageService")
public class AgentUserMessageServiceImpl implements IMessageService {

    private Logger logger = LoggerFactory.getLogger(AgentUserMessageServiceImpl.class);

    private final int TYPE_REG = 1;//经纪人注册发送验证码
    private final int TYPE_CELLPHONE_FIND = 2;//经纪人手机找回密码短信验证码
    private final int TYPE_EMAIL_FIND = 3;//经纪人邮箱找回密码短信验证码
    private final int TYPE_EMAIL_BIND = 4;//经纪人绑定邮箱验证码
    private final int TYPE_CELLPHONE_BIND = 5;//经纪人绑定手机验证码
    private final int TYPE_INVITE_SALESMAN = 6;//经纪人邀请常用业务员加入

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    public void processMessage(Message message) {
        try{
            Map<String, String> params = new HashMap<String, String>();
            String tplValue = URLEncoder.encode("#i_c#", ENCODING) + "="
                    + URLEncoder.encode(message.getCode(), ENCODING);
            params.put("tpl_id", "1835370");
            params.put("tpl_value", tplValue);
            String title = null;
            String content = "您的本次验证码：" + message.getCode() + ",10分钟内输入有效";
            params.put("content", content);
            String tmpl = "qbds_email.ftl";
            switch (message.getId()) {
                case TYPE_REG:
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_CELLPHONE_FIND:
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_EMAIL_FIND:
                    title = "找回密码-企保大师";
                    emailService.handle(message.getToEmail(), null, title, content, tmpl, message);
                    break;
                case TYPE_EMAIL_BIND:
                    title = "邮箱绑定-企保大师";
                    emailService.handle(message.getToEmail(), null, title, "邮箱绑定激活地址：" + message.getContent(), tmpl, message);
                    break;
                case TYPE_CELLPHONE_BIND:
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_INVITE_SALESMAN:
                    title = "用户邀请-企保大师";
                    content = "您的合作伙伴" + message.getContent() + "正在使用企保大师中介服务，他邀请您来承接他的业务。前往<a href=\"http://www.qibaozz.com\">www.qibaozz.com</a>注册保险公司账号，即可承接客户业务。";
                    emailService.handle(message.getToEmail(), null, title, content, tmpl, message);
                    break;
                default:
                    logger.error("消息id不存在");
                    break;
            }
        } catch (Exception ex) {
            logger.error("经经人用户中心信息异常", ex);
        }
    }
}
