package com.lovo.mq.entity;

import java.io.Serializable;

/**
 *用户实体
 */
public class UserEntity implements Serializable {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
