package com.msg.mc.service.impl;

import com.msg.mc.model.qbzz.BaseCompany;
import com.msg.mc.model.qbzz.Users;
import com.msg.mc.service.send.EmailService;
import com.msg.mc.dao.BaseCompanyDao;
import com.msg.mc.dao.TendersDao;
import com.msg.mc.dao.UsersDao;
import com.msg.mc.model.qbzz.Tenders;
import com.msg.mc.service.IMessageService;
import com.msg.mc.service.impl.helper.QbzzMessageHelper;
import com.msg.mc.service.send.MsgService;
import com.msg.mc.service.send.SmsService;
import com.msg.mc.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author  cloudy
 * @version  1.0
 * @date 17/6/7 上午10:08
 */
@Component("qbzzMessageService")
public class QbzzMessageServiceImpl implements IMessageService {

    private Logger logger = LoggerFactory.getLogger(QbzzMessageServiceImpl.class);

    /**
     * 参保公司发标
     */
    private final int TYPE_PUBLISH_TENDER = 1;

    /**
     * 保险公司报价
     */
    private final int TYPE_PRICE = 3;

    /**
     * 选中保险公司
     */
    private final int TYPE_SELECTED = 4;

    /**
     * 已付款
     */
    private final int TYPE_IS_PAY = 5;

    /**
     * 生成保单
     */
    private final int TYPE_GENERATE_POLICY	= 7;

    /**
     * 保险标已修改
     */
    private final int TYPE_UPDATE_TENDER = 8;

    /**
     * 退回报价
     */
    private final int TYPE_RETURN_PRICE = 9;

    /**
     * 设置共保份额完成
     */
    private final int TYPE_COINSURANCE = 11;

    /**
     * 补充资料提醒
     */
    private final int TYPE_FURTHER_INFORMATION = 12;

    @Autowired
    private MsgService msgService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private QbzzMessageHelper messageHelper;

    @Autowired
    private TendersDao tendersDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BaseCompanyDao baseCompanyDao;

    public void processMessage(Message message) {
        Tenders tenders = null;
        Users users = null;
        if(message.getTendersId() != null) {
            tenders = tendersDao.findTendersById(message.getTendersId());
            logger.info("标信息：" + tenders.toString());
            if (tenders == null) return;
            users = usersDao.findUsersById(tenders.getCreatedBy());
            if (users == null) return;
        }
        Map<String, String> map = null;
        String emailTmp = "email.ftl";
        try {
            switch (message.getId().intValue()) {
                case TYPE_PUBLISH_TENDER:
                    map = messageHelper.publishTender(tenders.getTenderName(), tenders.getTendkey());
                    break;
                case TYPE_PRICE:
                    Users formUsers = usersDao.findUsersById(message.getFromUserId());
                    logger.info("保险公司用户信息：" + formUsers.toString());
                    BaseCompany company = baseCompanyDao.findBaseCompanyById(formUsers.getCompanyId());
                    logger.info("保险公司用户公司信息：" + company.toString());
                    map = messageHelper.doPrice(tenders.getTenderName(), tenders.getTendkey(), company.getName());
                    break;
                case TYPE_UPDATE_TENDER:
                    map = messageHelper.updateTender(tenders.getTenderName(), tenders.getTendkey());
                    break;
                case TYPE_GENERATE_POLICY:
                    map = messageHelper.generatePolicy(tenders.getTenderName(), tenders.getTendkey());
                    break;
                case TYPE_FURTHER_INFORMATION:
                    map = messageHelper.furtherInformation(tenders.getTenderName(), tenders.getTendkey());
                    break;
                case TYPE_RETURN_PRICE:
                    map = messageHelper.returnPrice(tenders.getTenderName(), tenders.getTendkey());
                    break;
                case TYPE_SELECTED:
                    map = messageHelper.selected(tenders.getTenderName(), tenders.getTendkey());
                    break;
                case TYPE_IS_PAY:
                    map = messageHelper.isPay(tenders.getTenderName(), tenders.getTendkey());
                    break;
                default:
                    logger.error("消息id不存在");
                    break;
            }
            sendMessage(message, users, map, emailTmp);
        } catch (Exception ex) {
            logger.error("发送经纪人信息异常  ",ex);
        }
    }

    public void sendMessage(Message message, Users users, Map<String, String> map, String emailTmp) {
        if(message.getToUserId() != null && message.getToUserId().size() > 0) {
            for(Integer toUserId : message.getToUserId()) {
                Users toUser = usersDao.findUsersById(toUserId);
                if(toUser != null) {
                    sendMessageByType(message.getMsgType(), toUserId, map, toUser.getCellphone(),
                            toUser.getEmail(), toUser.getName(), emailTmp, message);
                }
            }
        } else if(message.getFromUserId() != null) {
            sendMessageByType(message.getMsgType(), users.getId(), map, users.getCellphone(),
                    users.getEmail(), users.getName(), emailTmp, message);
        } else if(message.getCellphone() != null) {
            smsService.handleYunpian(users.getCellphone(), map.get("content"), message);
        }
    }

    public void sendMessageByType(Integer msgType, Integer toUserId, Map<String, String> map,
                                  String cellphone, String email, String name, String emailTmp, Message message) {
        if(msgType.equals(1)) {
            msgService.handle(toUserId, map.get("content"));
        } else if(msgType.equals(2)) {
            smsService.handleTplYunPian(cellphone, map, message);
        } else if(msgType.equals(3)) {
            emailService.handle(email, name, map.get("title"), map.get("content"), emailTmp, message);
        } else if(msgType.equals(4)) {
            msgService.handle(toUserId, map.get("content"));
            emailService.handle(email, name, map.get("title"), map.get("content"), emailTmp, message);
            smsService.handleTplYunPian(cellphone, map, message);
        } else if(msgType.equals(5)) {
            msgService.handle(toUserId, map.get("content"));
            smsService.handleTplYunPian(cellphone, map, message);
        } else if(msgType.equals(6)) {   //只发站内信与邮件
            msgService.handle(toUserId, map.get("content"));
            emailService.handle(email, name, map.get("title"), map.get("content"), emailTmp, message);
        }
    }
}
