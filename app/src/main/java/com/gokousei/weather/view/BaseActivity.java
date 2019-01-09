package com.gokousei.weather.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gokousei.weather.utils.ToastUtils;

/**
 *
 */
public class BaseActivity extends AppCompatActivity {

    public static Context mApplicationContext;

    int coordinate[] = {0, 0};
    int x, y;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        mApplicationContext = getApplicationContext();
//        translucentStatusNavigation();
    }

    /**
     * 检查应用权限是否具有权限，如果被没有权限则提示打开权限
     * 返回boolean值用以判断是否具有权限
     *
     * @param permissions 待检查的权限
     * @param message     权限的名称
     * @param activity    检查权限的Activity
     */
    protected boolean myCheckPermissions(String permissions, String message, Activity activity) {
        if (ContextCompat.checkSelfPermission(this, permissions)
                != PackageManager.PERMISSION_GRANTED) {
//            myRequestPermissions(permissions);
            showPermissionDeniedDialog(message, activity);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 申请一组权限
     *
     * @param permissions 待申请的权限组
     */
    protected void myRequestPermissions(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(this,
                permissions, requestCode);
    }

    /**
     * 申请一个权限
     *
     * @param permissions 待申请的权限
     * @param message     带申请权限的名称
     * @param activity    申请权限的Activity
     */
    protected void myRequestPermissions(String permissions, String message, Activity activity) {
        if (ContextCompat.checkSelfPermission(this,
                permissions) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permissions)) {
                showPermissionDeniedDialog(message, activity);
                ToastUtils.showToast(this, "true");
            } else {
                ToastUtils.showToast(this, "false");
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{permissions}, 1);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限得到许可
                } else {
                    //权限被拒绝
                }
                return;
            }
        }
    }

    /**
     * 权限拒绝和没有权限是显示的提示
     *
     * @param message  提示信息中的权限名称
     * @param activity 发起前线申请或检查的Activity
     */
    private void showPermissionDeniedDialog(String message, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("运行异常");
        builder.setMessage("使用此功能所必须的”" + message + "“权限无法获取，" +
                "请点击“设置”按钮在弹出的设置界面打开权限。\n点击”关闭“按钮将返回上个界面。");
        builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
                activity.finish();
            }
        });
        builder.show();
    }

    /**
     * 跳转到app的设置界面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    //设置透明状态栏和导航栏
    private void translucentStatusNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //判断SDK版本
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {//判断触摸事件是否是点击
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹球或者实体按键会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {//判断是否隐藏输入法键盘
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {//判断传递的view是否是EditText
            if (coordinate[0] == 0 && coordinate[1] == 0) {
                v.getLocationOnScreen(coordinate);//获得view的坐标
                x = coordinate[0] + v.getWidth()+140;
                y = coordinate[1] + v.getHeight()+164;
            }
            //通过点击的坐标和view的坐标对比判断点击的是否EditText
            if (event.getRawX() < x && event.getRawX() > coordinate[0] &&
                    event.getRawY() < y && event.getRawY() > coordinate[1]) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                coordinate[0] = 0;
                coordinate[1] = 0;
                x = 0;
                y = 0;
                if (v.isFocused())
                    v.clearFocus();
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) mApplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            return ni.isAvailable();
        }
        return false;
    }
}
