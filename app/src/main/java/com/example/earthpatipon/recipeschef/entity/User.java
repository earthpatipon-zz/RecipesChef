package com.example.earthpatipon.recipeschef.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 *
 * Class for User entity
 *
 * Attribute: userID, userName, passWord
 * Constructor: with all attributes
 * Getters and Setters: all attributes
 *
 */
@Entity
public class User {

    /**
     * Constructor
     */
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserID")
    private int userId;

    @ColumnInfo(name = "Username")
    private String userName;

    @ColumnInfo(name = "Password")
    private String passWord;

    /**
     * getters and setters
     */
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
