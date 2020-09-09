package com.senior.wiet.lib.localstorage.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.senior.wiet.lib.localstorage.room.RoomConstant;
import com.senior.wiet.lib.localstorage.room.entities.User;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by lance on 18/February/2020.
 */
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(User... users);

    @Update
    int update(User... users);

    @Delete
    int delete(User... users);

    @Query("SELECT * FROM " + RoomConstant.Tables.User)
    Maybe<List<User>> getUsers();
}
