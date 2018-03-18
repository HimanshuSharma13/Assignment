package com.assignment2.rest;

import com.assignment2.model.pojo.WeatherDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Himanshu Sharma on 1/24/2018.
 */

public interface ApiInterface {
    //  http://api.openweathermap.org/data/2.5/forecast/daily?q=Philadelphia&mode=json&units=metric&cnt=16&APPID=f279ee6273c82ce2e30067a8d9511fae
//    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyCxeqAHsq4WlWZ1E4qgu7bM7bO-6GyxMjs")
    @GET("daily?q=Philadelphia&mode=json&units=metric&cnt=16&APPID=f279ee6273c82ce2e30067a8d9511fae")
    Call<WeatherDetail> getWeatherDetails();

    @GET("/forecast")
    void getWeatherInfo (@Query("lat") String latitude,
                         @Query("lon") String longitude,
                         @Query("cnt") String cnt,
                         @Query("appid") String appid,
                         Callback<WeatherDetail> cb);


}
