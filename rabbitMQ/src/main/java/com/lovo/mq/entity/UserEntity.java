package com.lovo.mq.entity;

import java.io.Serializable;

/**
 *
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
