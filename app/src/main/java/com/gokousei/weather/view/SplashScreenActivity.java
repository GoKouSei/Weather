package com.gokousei.weather.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.data.DataController;
import com.gokousei.weather.net.ApiRealize;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashScreenActivity extends BaseActivity {
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myRequestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if ((DataController.getInstance().loadWeatherSP(
                getApplicationContext(), WeatherActivity.SHARED_PREFERENCES_KEY) == null))
            ApiRealize.getWeather(new Observer<Weather>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Weather weather) {
                    DataController.getInstance().saveWeatherSP(getApplicationContext(), weather, WeatherActivity.SHARED_PREFERENCES_KEY);
                    DataController.getInstance().saveLocation(getApplicationContext(), "beijing");
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            }, "beijing");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(mContext, WeatherActivity.class));
                    finish();
                }
            }, 2000);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
