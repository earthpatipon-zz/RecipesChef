package com.example.earthpatipon.recipeschef.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.earthpatipon.recipeschef.entity.User;
import com.example.earthpatipon.recipeschef.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
<<<<<<< HEAD:app/src/main/java/com/example/earthpatipon/recipeschef/database/UserDatabase.java
                            UserDatabase.class, "User.db")
                            .allowMainThreadQueries().build();
=======
                            AppDatabase.class, "User.db")
                            .build();
>>>>>>> 316c777d996aa5ef31a6d695014d58dfa8034cb5:app/src/main/java/com/example/earthpatipon/recipeschef/database/AppDatabase.java
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
