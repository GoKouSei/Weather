package com.gokousei.weather.net;

import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.bean.WeatherForecast;
import com.gokousei.weather.bean.WeatherNow;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

;

public class ApiRealize {
    private static final String API_KEY = "5359e60d0df242bfad01844b0a30b183";

    public static void subscribeObserver(@NonNull Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public static void subscribeConsumer(@NonNull Observable observable, Consumer consumer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(consumer);
    }

    public static void getWeather(Observer<Object> observer, String location,boolean isUse) {
        Observable.concat(ApiResult.getApiInstance().getWeatherNow(location, API_KEY)
                , ApiResult.getApiInstance().getWeatherForecast(location, API_KEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public static void getWeather(Observer<Weather> observer, String location) {
        subscribeObserver(ApiResult.getApiInstance().getWeather(location, API_KEY), observer);
    }

    public static void getWeather(Consumer<Weather> consumer, String location) {
        subscribeConsumer(ApiResult.getApiInstance().getWeather(location, API_KEY), consumer);
    }

    public static void getWeatherNow(Consumer<WeatherNow> consumer, String location) {
        subscribeConsumer(ApiResult.getApiInstance().getWeatherNow(location, API_KEY), consumer);
    }

    public static void getWeatherNow(Observer<WeatherNow> observer, String location) {
        subscribeObserver(ApiResult.getApiInstance().getWeatherNow(location, API_KEY), observer);
    }

    public static void getWeatherForecast(Consumer<WeatherForecast> consumer, String location) {
        subscribeConsumer(ApiResult.getApiInstance().getWeatherForecast(location, API_KEY), consumer);
    }

    public static void getWeatherForecast(Observer<WeatherForecast> observer, String location) {
        subscribeObserver(ApiResult.getApiInstance().getWeatherForecast(location, API_KEY), observer);
    }
}
