package com.example.earthpatipon.recipeschef.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.earthpatipon.recipeschef.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query ("SELECT * FROM User WHERE Username = :username LIMIT 1")
    User findByName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("DELETE FROM User")
    void deleteAll();

}
