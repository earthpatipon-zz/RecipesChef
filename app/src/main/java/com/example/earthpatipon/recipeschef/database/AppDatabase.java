package com.example.earthpatipon.recipeschef.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.earthpatipon.recipeschef.dao.RecipeDao;
import com.example.earthpatipon.recipeschef.dao.UserDao;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.entity.User;

@Database(entities = {User.class, Recipe.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract RecipeDao recipeDao();
    public abstract UserDao userDao();


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Database.db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
