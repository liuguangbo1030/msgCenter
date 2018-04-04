package com.msg.mc.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import com.msg.mc.filter.MessageSendFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * web启动配置
 * @version 1.0
 * @author Administrator
 * @company 前海企保科技（深圳）有限公司
 * @date 2016年8月25日
 */
@Configuration
public class WebConfig {
	
	@Bean
    public FilterRegistrationBean sendFilter(MessageSendFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/send/*");
        return registration;
    }
}
