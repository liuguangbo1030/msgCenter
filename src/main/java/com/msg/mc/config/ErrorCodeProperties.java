package com.msg.mc.config;

import com.msg.mc.vo.ErrorCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cloudy
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 2016/9/1
 */
@Component("ErrorCodeProperties")
@ConfigurationProperties(prefix = "errorCode")
public class ErrorCodeProperties {
    private final List<ErrorCode> list = new ArrayList<ErrorCode>();

    public List<ErrorCode> getList() {
        return this.list;
    }
    
}
