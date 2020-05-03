package com.haphap.movieticketapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.haphap.movieticketapp.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    Long insertUser(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

}
