package com.msg.mc.service.impl.helper;

import com.msg.mc.config.YunpianProperties;
import com.msg.mc.dao.McSendFilterDao;
import com.msg.mc.dao.McSendMessageDao;
import com.msg.mc.util.InformationCenterUtil;
import com.msg.mc.model.mc.McSendFilter;
import com.msg.mc.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/13 下午7:09
 */
@Component
public class CommonMessageHelper {

    @Autowired
    private McSendFilterDao mcSendFilterDao;

    @Autowired
    private McSendMessageDao mcSendMessageDao;

    @Autowired
    private YunpianProperties yunpianProperties;

    private Logger logger = LoggerFactory.getLogger(CommonMessageHelper.class);

    public boolean filterMessageByToObject(String toObject) {
        if(InformationCenterUtil.isStrEmpty(toObject))
            return false;
        McSendFilter mcSendFilter = mcSendFilterDao.findMcSendFilterByToObject(toObject);
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("toObject", toObject);
        filter.put("startTime", DateUtils.getCurrentDate() + " 00:00:00");
        filter.put("endTime", DateUtils.getCurrentDate() + " 23:59:59");
        Integer countMcRecord = mcSendMessageDao.countMcSendRecord(filter);
        logger.info(toObject + "发送次数：" + countMcRecord);
        if(mcSendFilter != null || countMcRecord > yunpianProperties.getTotal())
            return true;
        return false;
    }
}