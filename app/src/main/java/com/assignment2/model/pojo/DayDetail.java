package com.assignment2.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himanshu Sharma on 3/16/2018.
 */

public class DayDetail {

    @SerializedName("dt")
    private int dt;
    @SerializedName("temp")
    private Temperature temperature;
//    private Main main;
    private java.util.List<Weather> weather = new ArrayList<Weather>();

    @SerializedName("clouds")
    private int clouds;
    @SerializedName("pressure")
    private float pressure;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
