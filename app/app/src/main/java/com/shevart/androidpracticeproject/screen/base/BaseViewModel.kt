package com.shevart.androidpracticeproject.screen.base

import androidx.lifecycle.ViewModel
import com.shevart.androidpracticeproject.util.RLogger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel : ViewModel() {
    protected val defaultErrorConsumer: Consumer<Throwable> =
        Consumer { defaultHandleException(it) }
    private val onClearedDisposable = CompositeDisposable()

    val defaultConsumerSubject = PublishSubject.create<Throwable>()

    override fun onCleared() {
        onClearedDisposable.clear()
        super.onCleared()
    }

    protected fun Disposable.addToClearedDisposable(): Disposable {
        onClearedDisposable.add(this)
        return this
    }

    protected fun defaultHandleException(e: Throwable) {
        RLogger.log(e)
        defaultConsumerSubject.onNext(e)
    }
}