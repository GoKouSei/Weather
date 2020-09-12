package com.gokousei.weather.utils.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.gokousei.weather.data.DataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationUtils {

    private LocationManager locationManager;
    private LocationUtilsListener listener = new LocationUtilsListener();

    private static class Instance {
        private static final LocationUtils locationInstance = new LocationUtils();
    }

    private LocationUtils() {
    }

    public static LocationUtils getInstance() {
        return Instance.locationInstance;
    }

    private String getBestProvider(LocationManager locationManager) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//设置定位精准度
        criteria.setAltitudeRequired(false);//是否要求海拔
        criteria.setBearingRequired(true);//是否要求方向
        criteria.setCostAllowed(true);//是否要求收费
        criteria.setSpeedRequired(true);//是否要求速度
        criteria.setPowerRequirement(Criteria.POWER_LOW);//设置电池耗电要求
        criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);//设置方向精确度
        criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);//设置速度精确度
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);//设置水平方向精确度
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);//设置垂直方向精确度
        return locationManager.getBestProvider(criteria, true);
    }

    private Location getLocation(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        String provider = getBestProvider(locationManager);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED) {
            return locationManager.getLastKnownLocation(provider);
        }
        return null;
    }

    public String getAddress(Context context) {
        Geocoder geocoder = new Geocoder(context.getApplicationContext());
        String address = "";
        String locality = "";
        Location location = getLocation(context.getApplicationContext());
        boolean flag = Geocoder.isPresent();
        if (flag && location != null) {
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 5);
                if (!addresses.isEmpty()) {
                    List<String> feature = new ArrayList<>();
                    for (int i = 0; i < addresses.size(); i++) {
                        feature.add(addresses.get(i).getFeatureName());
                        Log.d("GoKouSeiLog", "getAddress: " + addresses.get(i));
                        if (locality==null||locality.equals(""))
                            locality = addresses.get(0).getLocality();
                    }
                    if (!feature.isEmpty())
                        for (String county : feature) {
                            if (county.contains("县"))
                                address = county;
                        }

                    if (!feature.isEmpty() && address.equals(""))
                        for (String city : feature) {
                            if (city.contains("市"))
                                address = city;
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address.equals("") ? locality : address;
    }

    public String getAddress(Context context, Location location) {
        Geocoder geocoder = new Geocoder(context.getApplicationContext());
        String address = "";
        String locality = "";
        boolean flag = Geocoder.isPresent();
        if (flag && location != null) {
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 5);
                if (!addresses.isEmpty()) {
                    List<String> feature = new ArrayList<>();
                    for (int i = 0; i < addresses.size(); i++) {
                        feature.add(addresses.get(i).getFeatureName());
                        Log.d("GoKouSeiLog", "getAddress: " + addresses.get(i));
                        if (locality.equals(""))
                            locality = addresses.get(0).getLocality();
                    }
                    if (!feature.isEmpty())
                        for (String county : feature) {
                            if (county.contains("县"))
                                address = county;
                        }

                    if (!feature.isEmpty() && address.equals(""))
                        for (String city : feature) {
                            if (city.contains("市"))
                                address = city;
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address.equals("") ? locality : address;
    }

    public boolean checkCityChange(Context context, Location location) {
        String beforeCity = DataController.getInstance().loadLocation(context.getApplicationContext());
        String afterCity = getAddress(context, location);
        if (afterCity == null || afterCity.isEmpty())
            return true;
        Log.d("GoKouSeiLog", "checkCityChange: beforeCity=" + beforeCity);
        Log.d("GoKouSeiLog", "checkCityChange: afterCity=" + afterCity);
        return beforeCity.equals(afterCity);
    }

    public void startRequestLocation(Context context) {
        locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED) {
            locationManager.requestLocationUpdates(getBestProvider(locationManager), 30000, 100000, listener);
        }
    }

    public void stopRequestLocation(Context context) {
        locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(listener);
    }

    public Location getCurrentLocation() {
        return listener.getCurrent();
    }

}
