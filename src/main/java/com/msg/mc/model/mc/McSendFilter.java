package com.msg.mc.model.mc;

/**
 * @author cloudy ［Cloudy.liu@qibaozz.com］
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 17/6/13 下午6:41
 */
public class McSendFilter {
    private String toObject;
    private Integer status;

    public String getToObject() {
        return toObject;
    }

    public void setToObject(String toObject) {
        this.toObject = toObject;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
