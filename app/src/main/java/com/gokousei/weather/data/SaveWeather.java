package com.gokousei.weather.data;

import android.content.Context;

import java.io.FileOutputStream;

public class SaveWeather {
    private static SaveWeather saveWeather;
    public static volatile String locationSave = "";

    public static SaveWeather getSaveWeather() {
        if (saveWeather == null) {
            synchronized (SaveWeather.class) {
                if (saveWeather == null) {
                    saveWeather = new SaveWeather();
                }
            }
        }
        return saveWeather;
    }

    public SaveWeather() {
    }

    public void saveWeatherNow(final Context context, String location) {
        locationSave = location;
    }

    public void saveWeatherNowFile(Context context, String jsonData) {
//        File file=new File(Environment.getDataDirectory())
        if (!jsonData.equals("") && jsonData != null) {
            try {
                FileOutputStream fos = context.openFileOutput("Weather.json", Context.MODE_PRIVATE);
                fos.write(jsonData.getBytes());
                if (fos != null) {
                    fos.close();
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateWeatherNow(Context context) {
        saveWeatherNow(context, locationSave);
    }

    public void saveWeatherForecast(final Context context, String location) {
        locationSave = location;
    }

    public void saveWeatherForecastFile(Context context, String jsonData) {
        if (!jsonData.equals("") && jsonData != null) {
            try {
                FileOutputStream fos = context.openFileOutput("WeatherForecastSDK.json", Context.MODE_PRIVATE);
                fos.write(jsonData.getBytes());
                if (fos != null) {
                    fos.close();
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateWeatherForecast(Context context) {
        saveWeatherForecast(context, locationSave);
    }
}