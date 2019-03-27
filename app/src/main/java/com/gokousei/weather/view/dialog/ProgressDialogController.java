package com.gokousei.weather.view.dialog;

import android.content.Context;

public class ProgressDialogController {
    private volatile static ProgressDialogPuppet progressDialogPuppet;

    private static void getInstance(Context context, boolean cancelable) {
        if (progressDialogPuppet == null) {
            synchronized (ProgressDialogController.class) {
                if (progressDialogPuppet == null) {
                    progressDialogPuppet = new ProgressDialogPuppet(context, cancelable);
                }
            }
        }
        if (progressDialogPuppet != null && progressDialogPuppet.cancelable != cancelable) {
            synchronized (ProgressDialogController.class) {
                progressDialogPuppet = new ProgressDialogPuppet(context, cancelable);
            }
        }
    }

    public static void show(Context context, boolean cancelable) {
        getInstance(context, cancelable);
        progressDialogPuppet.obtainMessage(ProgressDialogPuppet.SHOW_PROGRESS_DIALOG).sendToTarget();
    }

    public static void dismiss() {
        if (progressDialogPuppet != null) {
            progressDialogPuppet.obtainMessage(ProgressDialogPuppet.DISMISS_PROGRESS_DIALOG).sendToTarget();
            progressDialogPuppet = null;
        }
    }
}
