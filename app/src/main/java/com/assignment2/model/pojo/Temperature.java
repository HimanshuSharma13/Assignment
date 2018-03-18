package com.assignment2.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Himanshu Sharma on 3/16/2018.
 */

public class Temperature {

    @SerializedName("day")
    private float day;
    @SerializedName("min")
    private float min;
    @SerializedName("max")
    private float max;
    @SerializedName("night")
    private float night;
    @SerializedName("eve")
    private String eve;
    @SerializedName("morn")
    private String morn;

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getMorn() {
        return morn;
    }

    public void setMorn(String morn) {
        this.morn = morn;
    }
}
