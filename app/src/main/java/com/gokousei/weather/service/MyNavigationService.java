package com.gokousei.weather.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.gokousei.weather.R;
import com.gokousei.weather.view.WeatherActivity;

public class MyNavigationService extends Service {
    public MyNavigationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = createForegroundNotification();
        startForeground(112205, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Notification createForegroundNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String notificationChannelId = "notification_channel_id_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Foreground Service Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(
                    notificationChannelId, channelName, importance);
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }
        Context context;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this, notificationChannelId);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("dawdsd");
        builder.setContentText("");
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(this, WeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        return builder.build();
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}