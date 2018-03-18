package com.assignment2;

import com.assignment2.model.pojo.WeatherDetail;

/**
 * Created by Himanshu Sharma on 3/16/2018.
 */

public interface WeatherListener {
    void onHideDialog();

    void onDeliverAllWeather(WeatherDetail weatherDetail);
}
