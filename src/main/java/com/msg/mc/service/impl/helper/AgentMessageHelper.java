package com.msg.mc.service.impl.helper;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
@Component
public class AgentMessageHelper {

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    /**
     * 发标
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> publishTender(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "发标通知 - 企保大师");
        String content = "您收到一个新的项目报价邀请：" + tenderName + "。登录企保后台，即可查看业务详情！（标号："+ tenderNum +"）";
        map.put("content", content);
        map.put("tpl_id", "1816264");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 报价
     * @param tenderName
     * @param tenderNum
     * @param companyName
     * @return
     */
    public Map<String, String> doPrice(String tenderName, String tenderNum, String companyName) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "保险公司已报价 - 企保大师");
        String content = "好消息！您发布的" + tenderName + "项目已收到" + companyName + "的报价,请登录查看报价。（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1816542");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#i_c#", ENCODING) + "="
                + URLEncoder.encode(companyName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 修改标
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> updateTender(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "保险标已经修改 - 企保大师");
        String content = "因客户变更投保资料，项目" + tenderName + "需要您重新报价，请尽快处理。（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1816272");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 设置共保份额完成
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> coinsurance(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "共保标确认 - 企保大师");
        String content = "客户已为共保项目" + tenderName + "确认了统一的费率及承保条件，请登录查看相应的承保份额（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1816276");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 生成保单
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> generatePolicy(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "投保成功 - 企保大师");
        String content = "投保成功！您发布的" + tenderName + "收到一家承保方的保单，您可在后台查看电子保单。（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1816278");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 补充资料
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> furtherInformation(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "补充资料提醒 - 企保大师");
        String content = "补充资料提醒！您发布的" + tenderName + "，保险方希望您补充更多资料。登录企保后台，即可查看反馈详情！（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1828006");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }


    /**
     * 退回重报
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> returnPrice(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "报价被退回修改 - 企保大师");
        String content = "因客户退回报价，项目" + tenderName + "需要您重新报价，请尽快处理。（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1828008");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 选中保险公司
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> selected(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "选中保险公司 - 企保大师");
        String content = "好消息！您在项目" + tenderName + "的报价已被选中，请在客户付款后为其生成保单。（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1828058");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }

    /**
     * 保费已经支付(只有招招平台有)
     * @param tenderName
     * @param tenderNum
     * @return
     */
    public Map<String, String> isPay(String tenderName, String tenderNum) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "保费已经支付 - 企保大师");
        String content = "客户付款成功！项目" + tenderName + "已付款，请在确认到账后，于起保日期前出具保单并扫描上传。（标号：" + tenderNum + ")";
        map.put("content", content);
        map.put("tpl_id", "1765210");
        String tplValue = URLEncoder.encode("#product#", ENCODING) + "="
                + URLEncoder.encode(tenderName, ENCODING) + "&"
                + URLEncoder.encode("#tenderid#", ENCODING) + "="
                + URLEncoder.encode(tenderNum, ENCODING);
        map.put("tpl_value", tplValue);
        return map;
    }
}
