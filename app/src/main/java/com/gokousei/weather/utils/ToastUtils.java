package com.gokousei.weather.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    //    保存的上次显示的信息
    private static String oldMessage;
    //    Toast开始显示时间
    private static long firstTime;
    //    Toast第二次显示的时间
    private static long secondTime;
    //    Toast对象
    private static Toast toast = null;

    /**
     * 短暂显示Toast信息
     *
     * @param context Context对象
     * @param message 需要显示的信息
     */
    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
            firstTime = System.currentTimeMillis();
        } else {
            secondTime = System.currentTimeMillis();
            if (message.equals(oldMessage)) {
                if (secondTime - firstTime > Toast.LENGTH_SHORT) {
                    toast.setText(message);
                    toast.show();
                }
            } else {
                oldMessage = message;
                toast.setText(message);
                toast.show();
            }
        }
        firstTime = secondTime;
    }

    /**
     * 根据传入的值显示Toast信息
     *
     * @param context Context对象
     * @param message 需要显示的信息
     * @param length  显示信息的时长
     */
    public static void showToast(Context context, String message, int length) {
        if (toast == null) {
            toast = Toast.makeText(context, message, length);
            toast.show();
            firstTime = System.currentTimeMillis();
        } else {
            secondTime = System.currentTimeMillis();
            if (message.equals(oldMessage)) {
                if (secondTime - firstTime > length) {
                    toast.setText(message);
                    toast.show();
                }
            } else {
                oldMessage = message;
                toast.setText(message);
                toast.show();
            }
        }
        firstTime = secondTime;
    }
}
