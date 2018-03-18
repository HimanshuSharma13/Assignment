package com.assignment2.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.assignment2.model.pojo.WeatherDetail;

/**
 * Created by Himanshu Sharma on 3/18/2018.
 */

@Database(entities = {WeatherDetail.class}, version = 1)
public abstract class WeatherAppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
