package com.gokousei.weather.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.databinding.WeatherForecastBinding;
import com.gokousei.weather.utils.TimeUtils;

import java.text.ParseException;
import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private List<Weather.HeWeather6Bean.DailyForecastBean> mData;

    public WeatherForecastAdapter(List<Weather.HeWeather6Bean.DailyForecastBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.weather_forecast, viewGroup, false);
        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WeatherForecastBinding binding = (WeatherForecastBinding) viewHolder.getBinding();
        try {
            viewHolder.textView.setText(TimeUtils.getDayAndWeek(mData.get(i).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binding.setForecast(mData.get(i));
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void updateData(List<Weather.HeWeather6Bean.DailyForecastBean> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private TextView textView;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            textView = ((WeatherForecastBinding) binding).weatherForecastDate;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}