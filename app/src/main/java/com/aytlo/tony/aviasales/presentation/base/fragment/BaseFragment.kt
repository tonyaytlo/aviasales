package com.aytlo.tony.aviasales.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.aytlo.tony.aviasales.presentation.base.error.ErrorHandler
import com.aytlo.tony.aviasales.presentation.base.error.ToastErrorView
import com.aytlo.tony.aviasales.presentation.base.viewmodel.BaseViewModel
import org.koin.android.ext.android.get

abstract class BaseFragment : Fragment() {

    private var runOnResume: Runnable? = null
    private var isAfterOnSavedState: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isAfterOnSavedState = false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = obtainViewModel()
        setupErrorHandling(viewModel.error)
    }

    private fun setupErrorHandling(errorLiveData: LiveData<Throwable>) {
        createErrorObserver()?.let {
            errorLiveData.observe(this, it)
        }
    }

    override fun onResume() {
        super.onResume()
        runOnResume?.run()
        runOnResume = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isAfterOnSavedState = true
    }

    fun postOnResume(run: Runnable) {
        if (isAfterOnSavedState) {
            runOnResume = run
        } else {
            run.run()
        }
    }

    protected open fun createErrorObserver(): Observer<Throwable>? =
        ErrorHandler(ToastErrorView(requireActivity()), get())

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun obtainViewModel(): BaseViewModel
}