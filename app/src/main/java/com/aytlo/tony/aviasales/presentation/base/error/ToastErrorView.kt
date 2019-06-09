package com.aytlo.tony.aviasales.presentation.base.error

import android.content.Context
import com.aytlo.tony.aviasales.data.model.ErrorBean
import retrofit2.HttpException

class ToastErrorView(private val context: Context) : ErrorView {
    override fun showAuthError(httpException: HttpException) {
    }

    override fun showProtocolError(errorBean: ErrorBean) {
    }

    override fun showNonProtocolError(httpException: HttpException) {
    }

    override fun showNetworkError(t: Throwable) {
    }

    override fun showUnexpectedError(t: Throwable) {
    }
}