package com.example.earthpatipon.recipeschef.utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.earthpatipon.recipeschef.database.AppDatabase;

public class App extends Application{

    private static App INSTANCE;

    private AppDatabase database;

    public static App getInstance(){
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
