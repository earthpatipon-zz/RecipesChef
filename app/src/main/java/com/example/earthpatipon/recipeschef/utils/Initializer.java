package com.example.earthpatipon.recipeschef.utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.earthpatipon.recipeschef.database.AppDatabase;

public class Initializer extends Application{

    private static Initializer INSTANCE;

    private AppDatabase database;

    public static Initializer getInitializer(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // create database
        database = AppDatabase.getInstance(getApplicationContext());
        INSTANCE = this;
    }

    public AppDatabase getDatabase(){
        return database;
    }
}
