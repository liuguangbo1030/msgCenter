package com.msg.mc.service.send;

import com.msg.mc.config.YunpianProperties;
import com.msg.mc.dao.McSendMessageDao;
import com.msg.mc.model.mc.McSendRecord;
import com.msg.mc.service.impl.helper.CommonMessageHelper;
import com.msg.mc.util.DateUtils;
import com.msg.mc.util.InformationCenterUtil;
import com.msg.mc.vo.Message;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private McSendMessageDao mcSendMessageDao;

    @Autowired
    private YunpianProperties yunpianProperties;

    @Autowired
    private CommonMessageHelper commonMessageHelper;

    /**
     * 发送模板的邮件
     * @param toEmail
     * @param toName
     * @param title
     * @param content
     * @param tmpl
     */
    public void handle(String toEmail, String toName, String title, String content, String tmpl, Message msg) {
        McSendRecord mcSendRecord = new McSendRecord();
        mcSendRecord.setToObject(toEmail);
        mcSendRecord.setContent(content);
        mcSendRecord.setPlatformId(msg.getPlatformId());
        mcSendRecord.setMessageId(msg.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", toName);
        map.put("content", content);
        map.put("date", DateUtils.getCurrentDate());
        map.put("email", toEmail);
        logger.info("发送邮件信息：" + map.toString());
        try {
            mcSendRecord.setStatus(2);
            if(!InformationCenterUtil.isStrEmpty(toEmail) && !commonMessageHelper.filterMessageByToObject(toEmail)) {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(this.sender);
                helper.setTo(toEmail);
                helper.setSubject(title);
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tmpl);
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
                helper.setText(html, true);
                if (yunpianProperties.getSend().equals(1)) {
                    mailSender.send(message);
                }
                mcSendRecord.setStatus(1);
            }
            mcSendMessageDao.insertMcSendRecord(mcSendRecord);
        } catch (Exception ex) {
            mcSendRecord.setStatus(2);
            mcSendMessageDao.insertMcSendRecord(mcSendRecord);
            logger.error("发送邮件异常!", ex);
        }
    }
}
