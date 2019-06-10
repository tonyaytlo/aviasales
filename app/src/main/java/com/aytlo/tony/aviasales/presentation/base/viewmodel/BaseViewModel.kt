package com.aytlo.tony.aviasales.presentation.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {

    open val error = MutableLiveData<Throwable>()

    @Suppress("TooGenericExceptionCaught")
    protected fun launchLoadingErrorJob(
        context: CoroutineContext = Dispatchers.Main,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(context) {
            try {
                error.postValue(null)
                block.invoke()
            } catch (t: Throwable) {
                error.postValue(t)
            }
        }
    }
}