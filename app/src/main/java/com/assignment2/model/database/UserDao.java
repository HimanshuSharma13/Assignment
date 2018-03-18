package com.assignment2.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.assignment2.model.pojo.WeatherDetail;

/**
 * Created by Himanshu Sharma on 3/18/2018.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    WeatherDetail getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    WeatherDetail loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    WeatherDetail findByCityName(String first, String last);

    @Insert
    void insertAll(WeatherDetail... users);

    @Delete
    void delete(WeatherDetail user);
}
