package org.michaelbel.app;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    public RxBus() {}

    private PublishSubject<Object> publishSubject = PublishSubject.create();

    public void send(Object object) {
        publishSubject.onNext(object);
    }

    public Observable <Object> toObservable() {
        return publishSubject;
    }
}