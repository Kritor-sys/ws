package com.study.ws.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    //头像
    private String avatar;

    /**
     * 这里一开始写成private ，导致访问不了setName方法，name 一直为空
     * @param name
     */
    public void setName(String name){
        this.name = name.trim();
    }
}
