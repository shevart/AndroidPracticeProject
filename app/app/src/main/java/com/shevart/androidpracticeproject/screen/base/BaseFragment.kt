package com.shevart.androidpracticeproject.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shevart.androidpracticeproject.util.RLogger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {
    private val onDestroyDisposables = CompositeDisposable()
    private val onViewDestroyDisposables = CompositeDisposable()
    private val onStopDisposables = CompositeDisposable()

    @LayoutRes
    protected abstract fun provideLayoutResId(): Int

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(provideLayoutResId(), container, false)
    }

    override fun onStop() {
        onStopDisposables.clear()
        super.onStop()
    }

    override fun onDestroy() {
        onDestroyDisposables.clear()
        super.onDestroy()
    }

    override fun onDestroyView() {
        onViewDestroyDisposables.clear()
        super.onDestroyView()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showError(e: Throwable) {
        showToast(e.cause?.localizedMessage ?: e.toString())
    }

    fun defaultHandleException(e: Throwable) {
        RLogger.log(e)
        showError(e)
    }

    @Suppress("unused")
    protected fun Disposable.disposeOnStop() = this.apply {
        onStopDisposables.add(this)
    }

    protected fun Disposable.disposeOnDestroy() = this.apply {
        onDestroyDisposables.add(this)
    }

    fun Disposable.disposeOnDestroyView() = this.apply {
        onViewDestroyDisposables.add(this)
    }
}