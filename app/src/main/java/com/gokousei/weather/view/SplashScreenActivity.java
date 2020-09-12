package com.gokousei.weather.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.data.DataController;
import com.gokousei.weather.net.ApiRealize;
import com.gokousei.weather.utils.location.LocationUtils;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.core.Observer;

public class SplashScreenActivity extends BaseActivity {
    Context mContext = this;
    String local;
    GetAddress getAddress = new GetAddress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myRequestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 1);
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
            String location = LocationUtils.getInstance().getAddress(mContext);
            if (location==null||location.equals(""))
                location = "shanghai";
            return location;
        }

        @Override
        protected void onPostExecute(String s) {
            if (!s.isEmpty() && !local.equals(s))
                ApiRealize.getWeather(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Weather weather) {
                        DataController.getInstance().saveWeatherSP(getApplicationContext(), weather, WeatherActivity.SHARED_PREFERENCES_KEY);
                        DataController.getInstance().saveLocation(getApplicationContext(), s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        startActivity(new Intent(mContext, WeatherActivity.class));
                        finish();
                    }
                }, s);
            else {
                startActivity(new Intent(mContext, WeatherActivity.class));
                finish();
            }
        }
    }
}
