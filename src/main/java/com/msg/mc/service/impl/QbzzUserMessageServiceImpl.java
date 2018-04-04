package com.msg.mc.service.impl;

import com.msg.mc.dao.UsersDao;
import com.msg.mc.model.qbzz.Users;
import com.msg.mc.service.IMessageService;
import com.msg.mc.service.send.EmailService;
import com.msg.mc.service.send.SmsService;
import com.msg.mc.service.send.MsgService;
import com.msg.mc.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  cloudy ［Cloudy.liu@qibaozz.com］
 * @version  1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 17/6/7 上午10:08
 */
@Component("qbzzUserMessageService")
public class QbzzUserMessageServiceImpl implements IMessageService {

    private Logger logger = LoggerFactory.getLogger(QbzzUserMessageServiceImpl.class);

    private final int TYPE_EMAIL_BIND = 21;//用户绑定邮箱验证码
    private final int TYPE_CELLPHONE_BIND = 22;//用户绑定手机号验证码
    private final int TYPE_INTEGRAL_EXCHANGE = 24;//参保用户积分兑换礼品消息
    private final int TYPE_INSURER_INVITE= 25;//保险方用户邀请其他成员加入

    private final int TYPE_REG = 11;//用户注册短信验证码
    private final int TYPE_CELLPHONE_FIND = 12;//用户找回密码手机验证码
    private final int TYPE_EMAIL_FIND = 13;//用户找回密码邮箱验证码

    private final int TYPE_INSURER_EMAIL_REG = 15;//保险公司用户注册邮箱验证码
    private final int TYPE_INSURER_CELLPHONE_APLLY_JOIN = 18;//保险公司申请加入业务部提醒
    private final int TYPE_CELLPHONE_UPLOAD_POLICY = 19;//上传保单时系统为其创建账号
    
    private final int TYPE_STANDARD_SEND_POLICY_REG_USER = 30;//标品下单完成并根据手机号自动创建账号发送保单号
    private final int TYPE_STANDARD_SEND_POLICY = 31;//标品下单完成发送保单号

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private UsersDao usersDao;

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    public void processMessage(Message message) {
        try {
            String title = null;
            String content = "";
            //发送标品保单号到用户
            if(message.getId() == TYPE_STANDARD_SEND_POLICY_REG_USER) {
                content = "【企保招招】保单号" + message.getPolicyNum() + "。账号" + message.getCellphone() + "，初始密码" + message.getContent() + "。qibaozz.com";
            } else if(message.getId() == TYPE_STANDARD_SEND_POLICY) {
                content = "【企保招招】保单号" + message.getPolicyNum() + "。手机号登录企保后台，即可查看保单详情";
            } else if(message.getId() == TYPE_INSURER_CELLPHONE_APLLY_JOIN){
                content = "【企保招招】成员" + message.getContent() + ",正在申请加入业务部，请尽快处理。官网:qibaozz.com";
            } else if(message.getId() == TYPE_CELLPHONE_UPLOAD_POLICY){
                content = "【企保招招】终于等到你！账号:"+message.getCellphone()+",初始密码:"+message.getContent()+",快登陆企保招招修改密码吧。官网:qibaozz.com";
            } else if(message.getId() == TYPE_STANDARD_SEND_POLICY_REG_USER){
                content = "【企保招招】保单号"+message.getPolicyNum()+"。账号"+message.getCellphone()+"，初始密码"+message.getContent()+"。";
            } else if(message.getId() == TYPE_STANDARD_SEND_POLICY){
                content = "【企保招招】保单号"+message.getPolicyNum()+"。手机号登录企保后台，即可查看保单详情";
            } else {
                content = "您的本次验证码：" + message.getCode() + ",10分钟内输入有效";
            }
            String tmpl = "email.ftl";
            Map<String, String> params = new HashMap<String, String>();
            String tplValue = URLEncoder.encode("#i_c#", ENCODING) + "="
                    + URLEncoder.encode(message.getCode(), ENCODING);
            params.put("tpl_id", "1782590");
            params.put("tpl_value", tplValue);
            params.put("content", content);
            List<Integer> toUserIds = null;
            switch (message.getId()) {
                case TYPE_EMAIL_BIND:
                    title = "绑定邮箱-企保招招";
                    emailService.handle(message.getToEmail(), null, title, "邮箱绑定激活地址：" +message.getContent(), tmpl, message);
                    break;
                case TYPE_CELLPHONE_BIND:
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_INTEGRAL_EXCHANGE:
                    toUserIds = message.getToUserId();
                    if(toUserIds != null && toUserIds.size() > 0) {
                        for (Integer userId : toUserIds) {
                            Users users = usersDao.findUsersById(userId);
                            params.put("tpl_id", "1827500");
                            params.put("tpl_value", null);
                            smsService.handleTplYunPian(users.getCellphone(), params, message);
                            msgService.handle(users.getId(), "恭喜您！积分兑换礼品成功，我们将在1-3个工作日内发送京东E卡卡号和密码到您手机。客服热线：400-830-1985");
                        }
                    }
                    break;
                case TYPE_INSURER_INVITE:
                    title = "用户邀请-企保招招";
                    content = "您的合作伙伴" + message.getContent() + "正在使用企保大师中介服务，他邀请您来承接他的业务。前往<a href=\"http://www.qibaozz.com\">www.qibaozz.com</a>注册保险公司账号，即可承接客户业务。";
                    emailService.handle(message.getToEmail(), null, title, content, tmpl, message);
                    break;
                case TYPE_REG:
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_CELLPHONE_FIND:
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_EMAIL_FIND:
                    title = "找回密码-企保招招";
                    emailService.handle(message.getToEmail(), null, title, content, tmpl, message);
                    break;
                case TYPE_INSURER_EMAIL_REG:
                    title = "用户注册-企保招招";
                    emailService.handle(message.getToEmail(), null, title, content, tmpl, message);
                    break;
                case TYPE_INSURER_CELLPHONE_APLLY_JOIN:
                    toUserIds = message.getToUserId();
                    if(toUserIds != null && toUserIds.size() > 0) {
                        for(Integer userId : toUserIds) {
                            Users users = usersDao.findUsersById(userId);
                            params.put("tpl_id", "1763854");
                            tplValue = URLEncoder.encode("#i_name#", ENCODING) + "="
                                    + URLEncoder.encode(users.getName(), ENCODING);
                            params.put("tpl_value", tplValue);
                            smsService.handleTplYunPian(users.getCellphone(), params, message);
                        }
                    } else if(message.getCellphone() != null) {
                        params.put("tpl_id", "1763854");
                        tplValue = URLEncoder.encode("#i_name#", ENCODING) + "="
                                + URLEncoder.encode(message.getContent(), ENCODING);
                        params.put("tpl_value", tplValue);
                        smsService.handleTplYunPian(message.getCellphone(), params, message);
                    }
                    break;
                case TYPE_CELLPHONE_UPLOAD_POLICY:
                    params.put("tpl_id", "1762318");
                    tplValue = URLEncoder.encode("#i_m#", ENCODING) + "="
                            + URLEncoder.encode(message.getCellphone(), ENCODING) + "&"
                            + URLEncoder.encode("#i_p#", ENCODING) + "="
                            + URLEncoder.encode(message.getContent(), ENCODING);
                    params.put("tpl_value", tplValue);
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_STANDARD_SEND_POLICY_REG_USER:
                    params.put("tpl_id", "1876752");
                    tplValue = URLEncoder.encode("#number#", ENCODING) + "="
                            + URLEncoder.encode(message.getPolicyNum(), ENCODING) + "&"
                            + URLEncoder.encode("#mobile#", ENCODING) + "="
                            + URLEncoder.encode(message.getCellphone(), ENCODING) + "&"
                            + URLEncoder.encode("#password#", ENCODING) + "="
                            + URLEncoder.encode(message.getContent(), ENCODING);
                    params.put("tpl_value", tplValue);
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
                case TYPE_STANDARD_SEND_POLICY:
                    params.put("tpl_id", "1876774");
                    tplValue = URLEncoder.encode("#number#", ENCODING) + "="
                            + URLEncoder.encode(message.getPolicyNum(), ENCODING);
                    params.put("tpl_value", tplValue);
                    smsService.handleTplYunPian(message.getCellphone(), params, message);
                    break;
            }
        } catch (Exception ex) {
            logger.error("招招用户中心发送信息异常!", ex);
        }
    }
}
