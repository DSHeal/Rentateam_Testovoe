package com.dsheal.rentateam_testovoe.model.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dsheal.rentateam_testovoe.model.pojo.User;

@Database(entities = User.class, version=1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_Name = "users_db";

    private static AppDatabase database;

    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_Name).build();
            }
            return database;
        }
    }
    //возвращает объект интерфейса Дао
    public abstract UsersDao usersDao();
}
