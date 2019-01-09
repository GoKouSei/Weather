package com.gokousei.weather.data;

import android.content.Context;

import com.gokousei.weather.bean.WeatherForecastSDK;
import com.gokousei.weather.bean.WeatherNowSDK;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadWeather {
    private static LoadWeather loadWeather;
    private static String jsonData = "";
    public static String location = "";

    public static LoadWeather getLoadWeather() {
        if (loadWeather == null) {
            synchronized (LoadWeather.class) {
                if (loadWeather == null) {
                    loadWeather = new LoadWeather();
                }
            }
        }
        return loadWeather;
    }

    public LoadWeather() {
    }

    public WeatherNowSDK getWeatherNow(Context context) {
        try {
            FileInputStream fis = context.openFileInput("Weather.json");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            if (fis != null) {
                fis.close();
            }
            jsonData = new String(bytes, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(jsonData, WeatherNowSDK.class);
    }

    public WeatherForecastSDK getWeatherForecast(Context context) {
        try {
            FileInputStream fis = context.openFileInput("WeatherForecastSDK.json");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            if (fis != null) {
                fis.close();
            }
            jsonData = new String(bytes, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(jsonData, WeatherForecastSDK.class);
    }
}
