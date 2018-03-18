package com.assignment2.model.pojo;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himanshu Sharma on 3/15/2018.
 */
@Entity
public class WeatherDetail {

    @SerializedName("city")
    private City city;
    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private String message;
    @SerializedName("cnt")
    private int count;
    @SerializedName("list")
    private List<DayDetail> results = new ArrayList<DayDetail>();


    boolean isFromDatabase;


    public boolean isFromDatabase() {
        return isFromDatabase;
    }

    public void setFromDatabase(boolean fromDatabase) {
        isFromDatabase = fromDatabase;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DayDetail> getList() {
        return results;
    }

    public void setList(List<DayDetail> results) {
        this.results = results;
    }
}
