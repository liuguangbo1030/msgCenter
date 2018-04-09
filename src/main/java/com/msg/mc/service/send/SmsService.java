package com.msg.mc.service.send;

import com.msg.mc.config.YunpianProperties;
import com.msg.mc.dao.McSendMessageDao;
import com.msg.mc.model.mc.McSendRecord;
import com.msg.mc.service.impl.helper.CommonMessageHelper;
import com.msg.mc.vo.Message;

import java.util.*;

import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
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
public class SmsService {

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    private Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private YunpianProperties yunpianProperties;

    @Autowired
    private McSendMessageDao mcSendMessageDao;

    @Autowired
    private CommonMessageHelper commonMessageHelper;

    public void handleYunpian(String mobile, String text, Message message) {
        McSendRecord mcSendRecord = new McSendRecord();
        mcSendRecord.setToObject(mobile);
        mcSendRecord.setContent(text);
        mcSendRecord.setPlatformId(message.getPlatformId());
        mcSendRecord.setMessageId(message.getId());
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("apikey", yunpianProperties.getKey());
            params.put("text", text);
            params.put("mobile", mobile);
            logger.info("发送短信信息：" + params.toString());
            mcSendRecord.setStatus(2);
            if (yunpianProperties.getSend().equals(1) && !commonMessageHelper.filterMessageByToObject(mobile)) {
                String result = post(yunpianProperties.getUrl(), params);
                mcSendRecord.setStatus(1);
                logger.info("发送短信返回信息：" + result.toString());
            }
            mcSendMessageDao.insertMcSendRecord(mcSendRecord);
        } catch(Exception ex) {
            mcSendRecord.setStatus(2);
            mcSendMessageDao.insertMcSendRecord(mcSendRecord);
            logger.error("发送短信异常", ex);
        }
    }

    public void handleTplYunPian(String mobile, Map<String, String> map, Message message) {
        McSendRecord mcSendRecord = new McSendRecord();
        mcSendRecord.setToObject(mobile);
        mcSendRecord.setContent(map.get("content"));
        mcSendRecord.setPlatformId(message.getPlatformId());
        mcSendRecord.setMessageId(message.getId());
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("apikey", yunpianProperties.getKey());
            params.put("mobile", mobile);
            params.put("tpl_id", map.get("tpl_id"));
            params.put("tpl_value", map.get("tpl_value"));
            logger.info("发送短信信息：" + params.toString());
            mcSendRecord.setStatus(2);
            if (yunpianProperties.getSend().equals(1) && !commonMessageHelper.filterMessageByToObject(mobile)) {
                String result = post(yunpianProperties.getTplUrl(), params);
                mcSendRecord.setStatus(1);
                logger.info("发送短信返回信息：" + result.toString());
            }
            mcSendMessageDao.insertMcSendRecord(mcSendRecord);
        } catch (Exception ex) {
            mcSendRecord.setStatus(2);
            mcSendMessageDao.insertMcSendRecord(mcSendRecord);
            logger.error("发送短信异常", ex);
        }
    }


    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

}