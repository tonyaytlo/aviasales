package com.aytlo.tony.aviasales.presentation.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    open val error = MutableLiveData<Throwable>()

    private fun createExceptionHandler(exceptionBlock: (() -> Unit)? = null): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            exceptionBlock?.invoke()
            error.postValue(exception)
        }
    }

    @Suppress("TooGenericExceptionCaught")
    protected fun launchLoadingErrorJob(
        exceptionBlock: (() -> Unit)? = null,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(createExceptionHandler(exceptionBlock)) {
            try {
                error.postValue(null)
                block.invoke()
            } catch (t: Throwable) {
                error.postValue(t)
            }
        }
    }
}