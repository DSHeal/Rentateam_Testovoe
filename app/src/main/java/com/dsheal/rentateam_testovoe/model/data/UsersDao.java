package com.dsheal.rentateam_testovoe.model.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dsheal.rentateam_testovoe.model.pojo.User;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);

    @Query("DELETE FROM users")
    void deleteAllUsers();
}
