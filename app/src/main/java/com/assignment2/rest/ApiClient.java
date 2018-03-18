package com.assignment2.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Himanshu Sharma on 1/25/2018.
 */

public class ApiClient {

    public static final String BASE_URL="http://api.openweathermap.org/data/2.5/forecast/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null) {
            Gson gson=new GsonBuilder().create();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        }
        return retrofit;

    }
}
