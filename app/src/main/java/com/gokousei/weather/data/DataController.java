package com.gokousei.weather.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.gokousei.weather.bean.Weather;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataController {
    private volatile static DataController dataController;
    private static SharedPreferences sharedPreferences;
    private static final String SHARED_PREFERENCES_NAME = "Weathers";
    private static final String LOCATION_KEY = "Location";

    private enum Type {
        Shared_Preferences, File
    }

    public static DataController getInstance() {
        if (dataController == null)
            synchronized (DataController.class) {
                if (dataController == null)
                    dataController = new DataController();
            }
        return dataController;
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getApplicationContext()
                    .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE | Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    private SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public void saveWeatherSP(Context context, Weather weather, String key) {
        save(context.getApplicationContext(), weather, key, Type.Shared_Preferences);
    }

    public Weather loadWeatherSP(Context context, String key) {
        return (Weather) load(context.getApplicationContext(), key, Type.Shared_Preferences);
    }

    public void saveLocation(Context context, String location) {
        getEditor(context).putString(LOCATION_KEY, location).apply();
    }

    public String loadLocation(Context context) {
        return getSharedPreferences(context).getString(LOCATION_KEY, "");
    }

    private void save(Context context, Object object, String key, Type type) {
        switch (type) {
            case Shared_Preferences:
                if (object != null)
                    try {
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(bao);
                        oos.writeObject(object);
                        oos.flush();
                        getEditor(context).putString(key,
                                Base64.encodeToString(bao.toByteArray(), Base64.DEFAULT)).apply();
                        if (bao != null)
                            bao.close();
                        if (oos != null)
                            oos.close();
                    } catch (IOException e) {
                        Log.d("GoKouSeiLog", "saveWeather: IOException=" + e.toString());
                        e.printStackTrace();
                    }
                Log.d("GoKouSeiLog", "saveWeather: 传入对象为空");
                break;
            case File:
                if (object != null)
                    try {
                        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(object);
                        oos.flush();
                        if (oos != null)
                            oos.close();
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                        Log.d("GoKouSeiLog", "saveWeather: IOException=" + e.toString());
                        e.printStackTrace();
                    }
                Log.d("GoKouSeiLog", "saveWeather: 传入对象为空");
                break;
        }
    }

    private Object load(Context context, String key, Type type) {
        switch (type) {
            case Shared_Preferences:
                try {
                    Object o;
                    String base64 = getSharedPreferences(context)
                            .getString(key, null);
                    if (TextUtils.isEmpty(base64))
                        return null;
                    ByteArrayInputStream bai = new ByteArrayInputStream(Base64.decode(base64, Base64.DEFAULT));
                    ObjectInputStream ois = new ObjectInputStream(bai);
                    o = ois.readObject();
                    if (bai != null)
                        bai.close();
                    if (ois != null)
                        ois.close();
                    return o;
                } catch (FileNotFoundException e) {
                    Log.d("GoKouSeiLog", "loadWeather: FileNotFoundException=" + e.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("GoKouSeiLog", "loadWeather: IOException=" + e.toString());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    Log.d("GoKouSeiLog", "loadWeather: ClassNotFoundException=" + e.toString());
                    e.printStackTrace();
                }
            case File:
                try {
                    Object o;
                    FileInputStream fis = new FileInputStream(key);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    o = ois.readObject();
                    if (ois != null)
                        ois.close();
                    if (fis != null)
                        fis.close();
                    return o;
                } catch (FileNotFoundException e) {
                    Log.d("GoKouSeiLog", "loadWeather: FileNotFoundException=" + e.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("GoKouSeiLog", "loadWeather: IOException=" + e.toString());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    Log.d("GoKouSeiLog", "loadWeather: ClassNotFoundException=" + e.toString());
                    e.printStackTrace();
                }
        }
        return null;
    }
}
