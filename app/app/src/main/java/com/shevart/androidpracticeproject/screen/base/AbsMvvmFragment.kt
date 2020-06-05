package com.shevart.androidpracticeproject.screen.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shevart.androidpracticeproject.util.subscribeOnIoObserveOnMain

abstract class AbsMvvmFragment<VM : ViewModel> : BaseFragment() {
    protected lateinit var viewModel: VM

    abstract fun provideViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider
            .AndroidViewModelFactory(activity!!.application)
            .create(provideViewModelClass());
        subscribeForErrors(viewModel)
    }

    private fun subscribeForErrors(viewModel: VM) {
        if (viewModel is BaseViewModel) {
            viewModel.defaultConsumerSubject
                .subscribeOnIoObserveOnMain()
                .subscribe(
                    this::onViewModelError,
                    this::defaultHandleException
                )
                .disposeOnDestroy()
        }
    }

    protected open fun onViewModelError(e: Throwable) {
        defaultHandleException(e)
    }
}