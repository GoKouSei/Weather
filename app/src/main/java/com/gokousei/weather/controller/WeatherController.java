package com.gokousei.weather.controller;

import android.content.Context;

import com.gokousei.weather.R;

public class WeatherController {
    private Context mContext;

    public WeatherController(Context context) {
        mContext = context;
    }

    public int getWeatherUI(int condCode) {
        switch (condCode) {
            case 100:
                return R.drawable.sun;
            case 101:
                return R.drawable.cloudy;
            case 102:
                return R.drawable.cloud;
            case 103:
            case 104:
                return R.drawable.cloudy_day;
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
                return R.drawable.wind;
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
                return R.drawable.tornado;
            case 300:
            case 301:
                return R.drawable.raining;
            case 302:
            case 303:
            case 304:
                return R.drawable.lightning;
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
                return R.drawable.raining;
            case 310:
            case 311:
            case 312:
                return R.drawable.rain_storm;
            case 313:
                return R.drawable.snow_rain;
            case 314:
            case 315:
                return R.drawable.raining;
            case 316:
            case 317:
            case 318:
                return R.drawable.rain_storm;
            case 399:
                return R.drawable.raining;
            case 400:
            case 401:
            case 402:
            case 403:
                return R.drawable.snowing;
            case 404:
            case 405:
            case 406:
                return R.drawable.snow_rain;
            case 407:
            case 408:
            case 409:
            case 410:
            case 499:
                return R.drawable.snowing;
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 505:
            case 506:
            case 507:
            case 508:
            case 509:
            case 510:
            case 511:
            case 512:
            case 513:
            case 514:
            case 515:
                return R.drawable.fog;
            case 900:
                return R.drawable.temperature_day;
            case 901:
                return R.drawable.winter_temperature;
            case 999:
                return R.drawable.sun;
            default:
                return R.drawable.sun;

        }
    }
}
