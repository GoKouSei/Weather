package com.gokousei.weather.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressDialogPuppet extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private NetLoadDialog netLoadDialog;

    public Context context;
    public boolean cancelable;
    private ProgressDialogCancelListener listener;

    public ProgressDialogPuppet(Context context1, boolean cancelable1, ProgressDialogCancelListener listener1) {
        context = context1;
        cancelable = cancelable1;
        listener = listener1;
        init();
    }

    public ProgressDialogPuppet(Context context1, boolean cancelable1) {
        context = context1;
        cancelable = cancelable1;
        initNoListener();
    }

    public void init() {
        if (netLoadDialog == null) {
            netLoadDialog = new NetLoadDialog(context);
        } else {
            netLoadDialog.setCancelable(cancelable);
            if (cancelable) {
                netLoadDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        listener.onCancelDialog();
                    }
                });
            }
            show();
        }
    }

    public void initNoListener() {
        if (netLoadDialog == null) {
            netLoadDialog = new NetLoadDialog(context);
        } else {
            netLoadDialog.setCancelable(cancelable);
        }
    }

    public void show() {
        if (netLoadDialog != null && !netLoadDialog.isShowing()) {
            netLoadDialog.show();

        }
    }

    public void dismiss() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (netLoadDialog != null && netLoadDialog.isShowing()) {
                    netLoadDialog.dismiss();
                    netLoadDialog = null;
                }
            }
        }, 800);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                show();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismiss();
                break;
        }
    }
}
