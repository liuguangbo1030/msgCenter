package com.msg.mc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cloudy
 * @version 1.0
 * @date 2016/9/6
 */
@Component
@ConfigurationProperties(prefix = "yunpian")
public class YunpianProperties {
    //key
    private String key;

    //url
    private String url;

    //用模板发送地址
    private String tplUrl;

    //是否发信短信和邮件（0：不发，1：正常发）
    private Integer send;

    //每天最多能接收的短信或邮箱数
    private Integer total;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTplUrl() {
        return tplUrl;
    }

    public void setTplUrl(String tplUrl) {
        this.tplUrl = tplUrl;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
