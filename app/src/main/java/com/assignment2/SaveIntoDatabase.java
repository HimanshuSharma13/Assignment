//package com.assignment2;
//
//import android.os.AsyncTask;
//
//import com.assignment2.model.pojo.WeatherDetail;
//
///**
// * Created by Himanshu Sharma on 3/16/2018.
// */
//
//
//public class SaveIntoDatabase extends AsyncTask<WeatherDetail, Void, Void> {
//
//
//    private final String TAG = SaveIntoDatabase.class.getSimpleName();
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected Void doInBackground(WeatherDetail... params) {
//
//        WeatherDetail flower = params[0];
//
//        flower.setPicture(bitmap);
//        mDatabase.addFlower(flower);
//
//        return null;
//    }
//}
