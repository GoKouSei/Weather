package com.gokousei.weather.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.gokousei.weather.R;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.data.DataController;
import com.gokousei.weather.net.ApiRealize;
import com.gokousei.weather.net.observer.ObserverGeneral;
import com.gokousei.weather.utils.location.LocationUtils;
import com.gokousei.weather.view.WeatherActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyNavigationService extends Service {
    ScheduledExecutorService scheduledService;
    Context mContext = this;
    String location;
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    Weather.HeWeather6Bean weatherBean;

    public MyNavigationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        location = DataController.getInstance().loadLocation(mContext);
        scheduledService = Executors.newScheduledThreadPool(2);
        builder = createForegroundNotification();
        ApiRealize.getWeather(new ObserverGeneral<Weather>() {
            @Override
            public void onSuccess(Weather weather) {
                weatherBean = weather.getHeWeather6().get(0);
                Log.d("GoKouSeiLog", "onSuccess: " + weatherBean.getBasic().getParent_city());
                update(weatherBean, builder);
                startForeground(112205, builder.build());
            }

            @Override
            public void onFinish(Status status) {

            }
        }, location);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("GoKouSeiLog", "run: location=" + DataController.getInstance().loadLocation(mContext));
        scheduledService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("GoKouSeiLog", "run: service");
                boolean isChange = LocationUtils.getInstance().checkCityChange(mContext, LocationUtils.getInstance().getCurrentLocation());
                if (!isChange) {
                    location = LocationUtils.getInstance().getAddress(mContext,
                            LocationUtils.getInstance().getCurrentLocation());
                    if (location != null && !location.isEmpty()) {
                        DataController.getInstance().saveLocation(mContext, location);
                        Log.d("GoKouSeiLog", "run: location=" + DataController.getInstance().loadLocation(mContext));
                    }
                }
                ApiRealize.getWeather(new ObserverGeneral<Weather>() {
                    @Override
                    public void onSuccess(Weather weather) {
                        weatherBean = weather.getHeWeather6().get(0);
                        Log.d("GoKouSeiLog", "onSuccess: " + weatherBean.getBasic().getParent_city());
                        update(weatherBean, builder);
                        notificationManager.notify(112205, builder.build());
                    }

                    @Override
                    public void onFinish(Status status) {

                    }
                }, location);
            }
        }, 1, 10, TimeUnit.MINUTES);
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private NotificationCompat.Builder createForegroundNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String notificationChannelId = "notification_channel_id_01";
        String channelName = "Foreground Service Notification";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(
                    notificationChannelId, channelName, importance);
            notificationChannel.setShowBadge(false);
            notificationChannel.enableVibration(false);
            notificationChannel.enableLights(false);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }
        builder = new NotificationCompat.Builder(
                this, notificationChannelId);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("");
        builder.setContentText("");
        builder.setPriority(NotificationCompat.PRIORITY_LOW);
        builder.setShowWhen(false);
        Intent intent = new Intent(this, WeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        return builder;
    }

    private void update(Weather.HeWeather6Bean weatherBean, NotificationCompat.Builder builder) {
        builder.setContentTitle(weatherBean.getBasic().getAdmin_area() + "·" +
                weatherBean.getBasic().getParent_city() + "·" + weatherBean.getBasic().getLocation());
        builder.setContentText(weatherBean.getNow().getCond_txt() + "。体感温度"
                + weatherBean.getNow().getFl() + "℃。" + weatherBean.getNow().getWind_dir() +
                weatherBean.getNow().getWind_sc() + "级、风速" + weatherBean.getNow().getWind_spd() + "km/h。");
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}