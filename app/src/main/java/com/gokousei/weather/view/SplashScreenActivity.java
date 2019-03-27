package com.gokousei.weather.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.data.DataController;
import com.gokousei.weather.net.ApiRealize;
import com.gokousei.weather.utils.location.LocationUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashScreenActivity extends BaseActivity {
    Context mContext = this;
    String location;
    String local;
    GetAddress getAddress = new GetAddress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myRequestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        local = DataController.getInstance().loadLocation(mContext);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1)
            getAddress.execute();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    class GetAddress extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... voids) {
            location = LocationUtils.getInstance().getAddress(mContext);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (!location.isEmpty() && !local.equals(location))
                ApiRealize.getWeather(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Weather weather) {
                        DataController.getInstance().saveWeatherSP(getApplicationContext(), weather, WeatherActivity.SHARED_PREFERENCES_KEY);
                        DataController.getInstance().saveLocation(getApplicationContext(), location);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        startActivity(new Intent(mContext, WeatherActivity.class));
                        finish();
                    }
                }, location);
            else {
                startActivity(new Intent(mContext, WeatherActivity.class));
                finish();
            }
        }
    }
}
