package com.study.ws.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 会话消息实体
 */
@Data
public class Message implements Serializable {
    /**
     * 发送者
     */
    private User from;

    /**
     * 消息
     */
    private String message;

    /**
     * 接收者
     * 1：想指定窗口发送，to 接受user对象
     * 2：公共消息，to设为null
     */
    private User to;
    /**
     * 创建时间
     */
    private String time;

    public void setMessage(String message){
        this.message=message==null?"":message.replaceAll("\r\n|\r|\n", "");
    }
}
