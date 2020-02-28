package com.lovo.jedis;

import com.lovo.UserEntity;

import java.util.List;

public class ListBean {
    List<UserEntity> list;

    public List<UserEntity> getList() {
        return list;
    }

    public void setList(List<UserEntity> list) {
        this.list = list;
    }
}
