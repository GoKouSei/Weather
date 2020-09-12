package com.gokousei.weather.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.databinding.LifestyleBinding;

import java.util.List;

public class LifeStyleAdapter extends RecyclerView.Adapter<LifeStyleAdapter.ViewHolder> {

    private List<Weather.HeWeather6Bean.LifestyleBean> mData;

    public LifeStyleAdapter(List<Weather.HeWeather6Bean.LifestyleBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.lifestyle, viewGroup, false);
        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LifestyleBinding binding = (LifestyleBinding) viewHolder.getBinding();
        switch (i) {
            case 0:
                viewHolder.textView.setText("舒适指数:");
                break;
            case 1:
                viewHolder.textView.setText("穿衣指数:");
                break;
            case 2:
                viewHolder.textView.setText("感冒指数:");
                break;
            case 3:
                viewHolder.textView.setText("运动指数:");
                break;
            case 4:
                viewHolder.textView.setText("旅游指数:");
                break;
            case 5:
                viewHolder.textView.setText("紫\u2002外\u2002线:");
                break;
            case 6:
                viewHolder.textView.setText("洗车指数:");
                break;
            case 7:
                viewHolder.textView.setText("空气污染:");
                break;
        }
        binding.setLifestyle(mData.get(i));
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void updateData(List<Weather.HeWeather6Bean.LifestyleBean> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private TextView textView;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            textView = ((LifestyleBinding) binding).lifestyleType;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}
