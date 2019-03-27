package com.gokousei.weather.utils.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

public class LocationUtilsListener implements LocationListener {
    boolean isAvailable = false;
    Location lastLocation;

    @Override
    public void onLocationChanged(Location location) {
        if (location.getLatitude() == 0.0 && location.getLongitude() == 0.0)
//            filter out 0.0,0.0 locations
            return;
        Log.d("GoKouSeiLog", "onLocationChanged: location=" + location.getLatitude() + "  " + location.getLongitude());
        if (lastLocation != null)
            lastLocation.set(location);
        else lastLocation = location;
        isAvailable = true;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("GoKouSeiLog", "onStatusChanged: OUT_OF_SERVICE");
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("GoKouSeiLog", "onStatusChanged: TEMPORARILY_UNAVAILABLE");
                isAvailable = false;
                break;
            case LocationProvider.AVAILABLE:
                Log.d("GoKouSeiLog", "onStatusChanged: AVAILABLE");
                isAvailable = true;
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("GoKouSeiLog", "onProviderEnabled: user provider:" + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("GoKouSeiLog", "onProviderDisabled: don't support current provider:" + provider);
    }

    public Location getCurrent() {
        return isAvailable ? lastLocation : null;
    }
}
