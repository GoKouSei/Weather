package com.gokousei.weather.net;

import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.bean.WeatherForecast;
import com.gokousei.weather.bean.WeatherNow;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("weather/now")
    Observable<WeatherNow> getWeatherNow(@Query("location") String location
            , @Query("key") String key);

    @GET("weather/forecast")
    Observable<WeatherForecast> getWeatherForecast(@Query("location") String location
            , @Query("key") String key);

    @GET("weather?parameters")
    Observable<Weather> getWeather(@Query("location") String location
            , @Query("key") String key);
}
