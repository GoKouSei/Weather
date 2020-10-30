package com.gokousei.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.gokousei.weather.service.MyNavigationService;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("GoKouSeiLog", "onReceive: ww");
        switch (intent.getAction()) {
            case "com.gokousei.weather.action.START_SERVICE":
                Intent myIntent = new Intent(context, MyNavigationService.class);
                myIntent.putExtra("Foreground", "This is a foreground service.");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(myIntent);
                } else context.startService(myIntent);
                break;
            case "com.gokousei.weather.action.STOP_SERVICE":
                break;
        }
    }
}