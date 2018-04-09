package com.msg.mc.vo;

import java.util.List;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
public class Message {
    /**
     * 消息id
     */
    private Integer id;

    /**
     * 平台id(1：招招平台，2：招招用户中心，3：官网，4：企保大师，5：企保大师用户中心)
     */
    private Integer platformId;

    /**
     * 标id
     */
    private Integer tendersId;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 接收的用户id
     */
    private List<Integer> toUserId;

    /**
     * 发送的用户id
     */
    private Integer fromUserId;

    /**
     * 消息类型(1：只发送站内信，2：只发送短信，3：只发送邮件，4：全部都发，5：短信和站内信)
     */
    private Integer msgType;

    /**
     * 邮箱地址
     */
    private String toEmail;

    private String content;
    
    //保单号
    private String policyNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getTendersId() {
        return tendersId;
    }

    public void setTendersId(Integer tendersId) {
        this.tendersId = tendersId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getToUserId() {
        return toUserId;
    }

    public void setToUserId(List<Integer> toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    @Override
    public String toString() {
        return "AskMessages [id=" + id
                + ",platformId=" + platformId
                + ",tendersId=" + tendersId
                + ",toUserId=" + toUserId.toString()
                + ",fromUserId=" + fromUserId
                + ",cellphone=" + cellphone
                + ",code=" + code
                + ",msgType=" + msgType
                + ",content=" + content
                + ",toEmail=" + toEmail
                + ",policyNum=" + policyNum
                + "]";
    }

    /**
     * @return the policyNum
     */
    public String getPolicyNum() {
        return policyNum;
    }

    /**
     * @param policyNum the policyNum to set
     */
    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }
}
