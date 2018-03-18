package com.assignment2.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Himanshu Sharma on 3/15/2018.
 */

public class City {

    @SerializedName("id")
    private long city_id;
    @SerializedName("name")
    private String name;
    @SerializedName("coord")
    private Coordinate coordinate;
    @SerializedName("country")
    private String country;
    @SerializedName("population")
    private long population;

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
