package com.lovo.ui.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_user")//映射表面，默认就是类名
public class UserEntity {
    @Id //标注为ID
    //@GeneratedValue(strategy = GenerationType.AUTO)//ID生成策略
    @GenericGenerator(name = "userUUID",strategy = "uuid") //自定义了一个uuid生成策略
    @GeneratedValue(generator = "userUUID")
    @Column(name = "id",length = 32)//映射数据库的列
    private String userId;
    @Column(length = 48)
    private String userName;
    @Column(name = "age")
    private int userAge;
    @Column(columnDefinition = "text")
    private String message;

   @Column(columnDefinition = "Date")
    private String dateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
