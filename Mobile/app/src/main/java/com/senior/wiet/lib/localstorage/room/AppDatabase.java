package com.senior.wiet.lib.localstorage.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.senior.wiet.lib.localstorage.room.dao.UserDAO;
import com.senior.wiet.lib.localstorage.room.entities.User;

/**
 * Created by lance on 18/February/2020.
 */
@Database(entities = {User.class}, version = RoomConstant.DB_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = RoomConstant.DB_NAME;

    public abstract UserDAO getCategoryDAO();
}