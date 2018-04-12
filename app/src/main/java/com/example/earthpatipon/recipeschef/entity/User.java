package com.example.earthpatipon.recipeschef.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserID")
    private String userId;

    @ColumnInfo(name = "Username")
    private String userName;

    @ColumnInfo(name = "Password")
    private String passWord;

    /**
     * Constructor
     */
    public User(String username, String password) {
        this.userName = username;
        this.passWord = password;
    }

    /**
     * getters and setters
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }

}
