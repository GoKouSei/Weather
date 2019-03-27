package com.gokousei.weather.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.gokousei.weather.R;

public class NetLoadDialog extends Dialog {
    public NetLoadDialog(@NonNull Context context) {
        super(context, R.style.NetLoadDialog);
    }

    public NetLoadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public NetLoadDialog(@NonNull Context context, boolean cancelable) {
        super(context, R.style.NetLoadDialog);
        setCancelable(cancelable);
    }

    protected NetLoadDialog(@NonNull Context context, boolean cancelable, @NonNull DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());

    }

    private void init(@NonNull Context context) {

        //设置不可取消，点击其他区域不能取消
//        setCancelable(false);
//        setCanceledOnTouchOutside(false);
        setContentView(R.layout.net_load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

}
