package com.devyk.common.ext

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

fun <T> Observable<T>.subscribeIgnoreError(onNext: (T) -> Unit): Disposable?
        = subscribe(onNext, Throwable::printStackTrace)

//fun <T> Observable<T>.subscribeIgnoreError(onNext: (T) -> Unit): Disposable? {
//    return subscribe(onNext, Throwable::printStackTrace)
//}
//
//fun <T> Observable<T>.subscribeIgnoreError(onNext: (T) -> Unit): Disposable? {
//    val subscribe = subscribe(onNext, Throwable::printStackTrace)
//    return subscribe
//}