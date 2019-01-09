package com.gokousei.weather.net.observer;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 通用的Observer模板使用抽象方法onSuccess来处理onNext
 *
 * @param <T>
 */
public abstract class ObserverGeneral<T> implements Observer<T> {

    public ObserverGeneral() {
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
}
