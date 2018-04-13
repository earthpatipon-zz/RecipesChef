package com.example.earthpatipon.recipeschef.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.earthpatipon.recipeschef.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Query ("SELECT * FROM User WHERE userName LIKE :username LIMIT 1")
    User findAllByName(String username);

    @Query ("SELECT Password FROM User WHERE userName = username")
    User findPasswordByName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void deleteAllUsers(User user);

}
