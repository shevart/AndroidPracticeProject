package com.shevart.androidpracticeproject.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.functions.Cancellable

fun <T> LifecycleOwner.observeLiveData(
    liveData: LiveData<T>,
    callback: (value: T?) -> Unit
): Cancellable {
    val observer = Observer<T> { callback(it) }
    liveData.observe(this, observer)
    return Cancellable { liveData.removeObserver(observer) }
}

fun <T> LifecycleOwner.observeLiveDataNullSafety(
    liveData: LiveData<T>,
    callback: (value: T) -> Unit
): Cancellable {
    val observer = Observer<T> { valueNullable ->
        valueNullable?.let { value -> callback(value) }
    }
    liveData.observe(this, observer)
    return Cancellable { liveData.removeObserver(observer) }
}

fun <T> LifecycleOwner.observeLiveDataForceNonNull(
    liveData: LiveData<T>,
    callback: (value: T) -> Unit
): Cancellable {
    val observer = Observer<T> { valueNullable ->
        callback(valueNullable!!)
    }
    liveData.observe(this, observer)
    return Cancellable { liveData.removeObserver(observer) }
}
