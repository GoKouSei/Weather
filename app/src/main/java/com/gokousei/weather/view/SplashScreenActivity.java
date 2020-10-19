package com.gokousei.weather.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.data.DataController;
import com.gokousei.weather.net.ApiRealize;
import com.gokousei.weather.utils.location.LocationUtils;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SplashScreenActivity extends BaseActivity {
    Context mContext = this;
    String local = "";
    GetAddress getAddress = new GetAddress();
    private static final String SHARED_PREFERENCES_NAME = "Weathers";
    private static final String KEY = "PrivacyPolicy";
    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (checkPrivacyPolicy()) {
            showPrivacyPolicy();
        } else {
            getAddress.execute();
            startActivity(new Intent(mContext, WeatherActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1)
            getAddress.execute();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean checkPrivacyPolicy() {
        if (sharedPreferences == null)
            sharedPreferences = this.getApplicationContext()
                    .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE | Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY, -1) != 0;
    }

    public void showPrivacyPolicy() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("软件所需权限说明")
                .setMessage("本软件在使用过程中会请求位置权限，获取的位置信息只会用与获取当前位置的天气情况。")
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putInt(KEY, 0).apply();
                        myRequestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
    }

    class GetAddress extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String location = "";
            try {
                location = LocationUtils.getInstance().getAddress(mContext);
            } catch (Exception E) {
            }
            if (location == null || location.equals(""))
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
