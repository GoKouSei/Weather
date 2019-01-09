package com.gokousei.weather.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.databinding.WeatherForecastBinding;

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

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}