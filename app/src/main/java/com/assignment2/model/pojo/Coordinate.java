package com.assignment2.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Himanshu Sharma on 3/15/2018.
 */

public class Coordinate {

    @SerializedName("lon")
    private double longitude;
    @SerializedName("lat")
    private double latitude;
}
