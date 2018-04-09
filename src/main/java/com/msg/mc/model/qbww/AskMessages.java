package com.msg.mc.model.qbww;

import java.util.Date;

/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
public class AskMessages {
    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private String content;
    private Integer isRead;
    private Integer fromDeleted;
    private Integer toDeleted;
    private Date createdAt;
    private Date updatedAt;

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getFromDeleted() {
        return fromDeleted;
    }

    public void setFromDeleted(Integer fromDeleted) {
        this.fromDeleted = fromDeleted;
    }

    public Integer getToDeleted() {
        return toDeleted;
    }

    public void setToDeleted(Integer toDeleted) {
        this.toDeleted = toDeleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AskMessages [id=" + id
                + ",fromUserId=" + fromUserId
                + ",toUserId=" + toUserId
                + ",content=" + content
                + ",isRead=" + isRead
                + ",fromDeleted=" + fromDeleted
                + ",toDeleted=" + toDeleted
                + ",createdAt=" + createdAt
                + ",updatedAt=" + updatedAt
                + "]";
    }
}
