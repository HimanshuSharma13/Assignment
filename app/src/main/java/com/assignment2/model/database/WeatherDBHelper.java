package com.assignment2.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.assignment2.MainActivity;
import com.assignment2.WeatherListener;
import com.assignment2.model.helper.Constants;
import com.assignment2.model.pojo.City;
import com.assignment2.model.pojo.DayDetail;
import com.assignment2.model.pojo.Temperature;
import com.assignment2.model.pojo.Weather;
import com.assignment2.model.pojo.WeatherDetail;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Himanshu Sharma on 3/16/2018.
 */

public class WeatherDBHelper extends SQLiteOpenHelper {

    private final String TAG = MainActivity.SaveIntoDatabase.class.getSimpleName();
//    public static final String DATABASE_NAME = "WeatherForecast.db";
//    private static final int DATABASE_VERSION = 1;
//
//    public static final String WEATHER_TABLE_NAME = "weather";


    public WeatherDBHelper(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Constants.DATABASE.DROP_QUERY);
        onCreate(sqLiteDatabase);
    }

    public void addWeather(WeatherDetail weatherDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        City city = weatherDetail.getCity();
        List<DayDetail> list = weatherDetail.getList();
        for (DayDetail detail :
                list) {
            Temperature temp = detail.getTemperature();
            List<Weather> weatherList = detail.getWeather();
            Weather weather = weatherList.get(0);
            ContentValues values = new ContentValues();
            values.put(Constants.DATABASE.W_ID, weatherDetail.getCod());
            values.put(Constants.DATABASE.CITY, city.getName());
            values.put(Constants.DATABASE.COUNTRY, city.getCountry());
            values.put(Constants.DATABASE.WEATHER_MAIN, weather.getMain());
            values.put(Constants.DATABASE.WEATHER_DESCRIPTION, weather.getDescription());
            values.put(Constants.DATABASE.TEMP_MAX, temp.getMax());
            values.put(Constants.DATABASE.TEMP_MIN, temp.getMin());


            try {
                db.insert(Constants.DATABASE.TABLE_NAME, null, values);
            } catch (Exception e) {

            }
        }
        db.close();


    }

    public void fetchWeather(WeatherListener listener) {
        WeatherFetcher fetcher = new WeatherFetcher(listener, this.getWritableDatabase());
        fetcher.start();
    }

    public class WeatherFetcher extends Thread {

        private final WeatherListener mListener;
        private final SQLiteDatabase mDb;

        public WeatherFetcher(WeatherListener listener, SQLiteDatabase db) {
            mListener = listener;
            mDb = db;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_WEATHER_QUERY, null);

            List<DayDetail> list = new ArrayList<DayDetail>();
            final WeatherDetail weatherDetail = new WeatherDetail();
            City city = new City();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {

                        DayDetail dayDetail = new DayDetail();

                        city.setName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CITY)));
                        city.setCountry(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.COUNTRY)));
                        Temperature temp = new Temperature();
                        temp.setMax(Float.parseFloat(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TEMP_MAX))));
                        temp.setMin(Float.parseFloat(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TEMP_MIN))));
                        Weather weather = new Weather();
                        weather.setMain(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.WEATHER_MAIN)));
                        weather.setDescription(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.WEATHER_DESCRIPTION)));
                        ArrayList<Weather> weatherList = new ArrayList<Weather>();
                        weatherList.add(weather);
                        dayDetail.setWeather(weatherList);
                        dayDetail.setTemperature(temp);
                        list.add(dayDetail);


                    } while (cursor.moveToNext());
                    weatherDetail.setList(list);
                    weatherDetail.setCity(city);
//                    publishWeather(weatherDetail);
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllWeather(weatherDetail);
                    mListener.onHideDialog();
                }
            });
        }

//        public void publishWeather(final WeatherDetail weather) {
//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mListener.onDeliverWeather(weather);
//                }
//            });
//        }
    }
}
