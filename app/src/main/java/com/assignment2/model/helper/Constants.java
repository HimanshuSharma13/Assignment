package com.assignment2.model.helper;

/**
 * Created by Himanshu Sharma on 3/16/2018.
 */

public class Constants {

    public static final class DATABASE {

        public static final String DB_NAME = "WeatherForecast";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "weather";

        public static final String DROP_QUERY = "DROP TABLE IF EXIST " + TABLE_NAME;

        public static final String GET_WEATHER_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String W_ID = "wId";
        public static final String DATE = "date";
        public static final String CITY = "city";
        public static final String COUNTRY = "country";
        public static final String WEATHER_MAIN = "weather_main";
        public static final String WEATHER_DESCRIPTION = "weather_description";
        public static final String TEMP_MAX = "temp_max";
        public static final String TEMP_MIN= "temp_min";


        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "" +
                "(" +
                W_ID + " INTEGER  not null," +
                DATE + " INTEGER  not null," +
                CITY + " TEXT not null," +
                COUNTRY + " TEXT not null," +
                WEATHER_MAIN + " TEXT not null," +
                WEATHER_DESCRIPTION + " TEXT ," +
                TEMP_MAX + " REAL not null," +
                TEMP_MIN + " REAL not null)";
    }
}
