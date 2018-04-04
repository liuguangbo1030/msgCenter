package com.msg.mc.util;

import com.alibaba.fastjson.JSON;
import com.msg.mc.config.ErrorCodeProperties;
import com.msg.mc.vo.ErrorCode;
import com.msg.mc.context.ServiceUtil;
import com.msg.mc.vo.HttpResult;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cloudy
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 2016/9/1
 */
@Component("resultUtil")
public class HttpResultUtil {

    private static List<ErrorCode> list = null;

    public static ErrorCode getErrorCode(Integer code) {
        ErrorCodeProperties errorCodeProperties = (ErrorCodeProperties) ServiceUtil.getBean("ErrorCodeProperties");
        list = errorCodeProperties.getList();
        if (list != null && list.size() > 0) {
            for (ErrorCode errorCode : list) {
                if (errorCode != null && code.equals(errorCode.getCode())) {
                    return errorCode;
                }
            }
        }
        return null;
    }

    public static String successResult() {
        ErrorCode errorCode = getErrorCode(0);
        return JSON.toJSONString(new HttpResult(errorCode.getCode(), errorCode.getMessage(), null));
    }

    public static String successResult(Object data) {
        ErrorCode errorCode = getErrorCode(0);
        return JSON.toJSONString(new HttpResult(errorCode.getCode(), errorCode.getMessage(), data));
    }

    public static String failureResult() {
        ErrorCode errorCode = getErrorCode(90001);
        return JSON.toJSONString(new HttpResult(errorCode.getCode(), errorCode.getMessage(), null));
    }

    public static String failureResult(Integer $code) {
        ErrorCode errorCode = getErrorCode($code);
        return JSON.toJSONString(new HttpResult(errorCode.getCode(), errorCode.getMessage(), null));
    }

    public static String getResult(Integer code) {
        ErrorCode errorCode = getErrorCode(code);
        return JSON.toJSONString(new HttpResult(errorCode.getCode(), errorCode.getMessage(), null));
    }
}
