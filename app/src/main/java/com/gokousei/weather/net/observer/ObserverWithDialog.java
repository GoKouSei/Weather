package com.gokousei.weather.net.observer;

import android.content.Context;

import com.gokousei.weather.utils.dialog.ProgressDialogCancelListener;
import com.gokousei.weather.utils.dialog.ProgressDialogPuppet;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class ObserverWithDialog<T> implements Observer<T>, ProgressDialogCancelListener {
    private ProgressDialogPuppet progressDialogPuppet;

    public Context context;
    public Disposable disposable;

    public enum Status {
        Complete, Error
    }

    public ObserverWithDialog(Context context1) {
        context = context1;
        progressDialogPuppet = new ProgressDialogPuppet(context1, false, this);
    }

    public void showProgressDialog() {
        if (progressDialogPuppet != null) {
            progressDialogPuppet.obtainMessage(ProgressDialogPuppet.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialogPuppet != null) {
            progressDialogPuppet.obtainMessage(ProgressDialogPuppet.DISMISS_PROGRESS_DIALOG).sendToTarget();
            progressDialogPuppet = null;
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
        showProgressDialog();
    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFinish(Status.Error);
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        onFinish(Status.Complete);
        dismissProgressDialog();
    }

    @Override
    public void onCancelDialog() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFinish(Status status);
}
