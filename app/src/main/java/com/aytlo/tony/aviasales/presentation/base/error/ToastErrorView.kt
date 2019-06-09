package com.aytlo.tony.aviasales.presentation.base.error

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.aytlo.tony.aviasales.data.model.ErrorBean
import retrofit2.HttpException

class ToastErrorView(private val context: Context) : ErrorView {

    override fun showAuthError(httpException: HttpException) = Unit

    override fun showProtocolError(errorBean: ErrorBean) {
        Toast.makeText(context, errorBean.message, LENGTH_LONG).show()
    }

    override fun showNonProtocolError(httpException: HttpException) {
        Toast.makeText(context, httpException.message(), LENGTH_LONG).show()
    }

    override fun showNetworkError(t: Throwable) {
        Toast.makeText(context, "Проверьте интенет соединение", LENGTH_LONG).show()
    }

    override fun showUnexpectedError(t: Throwable) {
        Toast.makeText(context, t.localizedMessage, LENGTH_LONG).show()
    }
}