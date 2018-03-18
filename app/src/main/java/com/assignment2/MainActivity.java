package com.assignment2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment2.model.database.WeatherDBHelper;
import com.assignment2.model.helper.Utils;
import com.assignment2.model.pojo.City;
import com.assignment2.model.pojo.DayDetail;
import com.assignment2.model.pojo.Temperature;
import com.assignment2.model.pojo.WeatherDetail;
import com.assignment2.rest.ApiClient;
import com.assignment2.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements WeatherListener {

    ApiInterface apiservice;
    RecyclerView recyclerView;
    WeatherRecyclerAdapter mAdapter;
    private int screen_width;
    String colorArray[];
    private ProgressDialog mDialog;
    private WeatherDBHelper mDatabase;
    List<DayDetail> weatherList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = new WeatherDBHelper(this);
        screen_width = getResources().getDisplayMetrics().widthPixels;
        colorArray = getResources().getStringArray(R.array.material_colors);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager1);
        mAdapter = new WeatherRecyclerAdapter(weatherList, screen_width, colorArray);
        recyclerView.setAdapter(mAdapter);
//        WeatherRecyclerAdapter adapter = new WeatherRecyclerAdapter(this);
//        recyclerView.setAdapter(adapter);
        Retrofit retrofit = ApiClient.getClient();
        apiservice = retrofit.create(ApiInterface.class);
        loadData();
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });


    }

    void loadData() {
        showProgressDialog();
        if (getNetworkAvailability()) {
            final Call<WeatherDetail> call = apiservice.getWeatherDetails();
            callWeatherApi(call);
        } else {
            getWeatherFromDatabase();

        }

    }

    private void getWeatherFromDatabase() {
        mDatabase.fetchWeather(this);
    }

    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getApplicationContext());
    }

    private void showProgressDialog() {
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Loading Weather Forecast...");
        mDialog.setCancelable(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.show();


    }


    void callWeatherApi(final Call<WeatherDetail> call) {


        call.enqueue(new Callback<WeatherDetail>() {
            @Override
            public void onResponse(Call<WeatherDetail> call, Response<WeatherDetail> response) {

                if (response.isSuccessful()) {
                    WeatherDetail weatherDetail = response.body();
                    City city = weatherDetail.getCity();
                    List<DayDetail> weatherList = weatherDetail.getList();
                    DayDetail dayDetail = weatherList.get(0);
                    Temperature temp = dayDetail.getTemperature();
                    SaveIntoDatabase task = new SaveIntoDatabase();
                    task.execute(weatherDetail);
                    Log.d("MainActivity ", "Response " + response.body());
//                    Log.d("MainActivity ", "Response City " + city.getName());
//                    Log.d("MainActivity ", "Response DayDetail " + temp.getMax() + " : " + temp.getMin());
                    ((TextView) findViewById(R.id.city)).setText(city.getName() + " , " + city.getCountry());
                    mAdapter.setDayDetailList(weatherList);
                    mAdapter.notifyDataSetChanged();

                } else {
                    //show error message
                }

                onHideDialog();

            }

            @Override
            public void onFailure(Call<WeatherDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                onHideDialog();
            }
        });

    }

    @Override
    public void onHideDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onDeliverAllWeather(WeatherDetail weatherDetail) {
        if(mAdapter==null){
            mAdapter = new WeatherRecyclerAdapter(weatherDetail.getList(), screen_width, colorArray);
            recyclerView.setAdapter(mAdapter);
        }
        else
        mAdapter.setDayDetailList(weatherDetail.getList());
        mAdapter.notifyDataSetChanged();
    }


    public class SaveIntoDatabase extends AsyncTask<WeatherDetail, Void, Void> {


        private final String TAG = SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(WeatherDetail... params) {

            WeatherDetail weatherDetail = params[0];

//            flower.setPicture(bitmap);
            mDatabase.addWeather(weatherDetail);

            return null;
        }
    }


}
