package com.assignment2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.assignment2.model.pojo.DayDetail;
import com.assignment2.model.pojo.Temperature;
import com.assignment2.model.pojo.Weather;


import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Himanshu Sharma on 3/16/2018.
 */

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    List<DayDetail> dayDetailList;

    int screenwidth;
    int width;
    int height;

    Bitmap image;
    WeakReference<Bitmap> bitmapWeakReference;
    Fragment fragment;
    String material_colors[];
    int i = -1;

//    private static final int TYPE_HEADER = 0;
//    private static final int TYPE_ITEM = 1;


    public WeatherRecyclerAdapter(List<DayDetail> imageInfoList, int screen_width, String[] colorArray) {
        this.dayDetailList = imageInfoList;
        this.screenwidth = screen_width;
        this.material_colors = colorArray;
//        screenwidth = context.getResources().getDisplayMetrics().widthPixels;
//        material_colors = context.getResources().getStringArray(R.array.material_colors);
//        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);

    }

    public List<DayDetail> getDayDetailList() {
        return dayDetailList;
    }

    public void setDayDetailList(List<DayDetail> dayDetailList) {
        this.dayDetailList = dayDetailList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder != null) {
            i = position % material_colors.length;
            holder.cardView.setCardBackgroundColor(Color.parseColor(material_colors[i]));
            List<Weather> weatherList = dayDetailList.get(i).getWeather();
            Temperature temp = dayDetailList.get(i).getTemperature();
            holder.textViewMax.setText(temp.getMax() + "");
            holder.textViewMin.setText(temp.getMin() + "");
            holder.textViewMain.setText(weatherList.get(0).getMain());
            holder.textViewDesc.setText(weatherList.get(0).getDescription());
//            ImageInfo imageInfo = dayDetailList.get(position);
//            width = (int) imageInfo.getWidth();
//            height = (int) imageInfo.getHeight();
//            float ratio = height * 1f / (float) width;
//            Log.d("WeatherRecyclerAdapter", "w/h " + width + "/" + height);
//            i++;


//            holder.imageView.setAspectRatioWidth(width);
//            holder.imageView.setAspectRatioWidth(height);
//            holder.imageView.getLayoutParams().width=screenwidth/3;
//            holder.imageView.requestLayout();


//            holder.imageView.setAspectRatioWidth(width);
//            holder.imageView.setAspectRatioHeight(height);


        }


    }

    @Override
    public int getItemViewType(int position) {
//        if (isPositionHeader(position))
//            return TYPE_HEADER;

        return position;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return dayDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMain, textViewDesc, textViewMax, textViewMin;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            textViewMain = (TextView) view.findViewById(R.id.main);
            textViewDesc = (TextView) view.findViewById(R.id.desc);
            textViewMax = (TextView) view.findViewById(R.id.max);
            textViewMin = (TextView) view.findViewById(R.id.min);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }


}



