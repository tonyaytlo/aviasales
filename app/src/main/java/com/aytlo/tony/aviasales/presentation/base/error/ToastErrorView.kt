package com.aytlo.tony.aviasales.presentation.base.error

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.data.model.ErrorBean
import com.aytlo.tony.aviasales.extension.android.content.showToast
import retrofit2.HttpException

class ToastErrorView(private val context: Context) : ErrorView {

    override fun showNetworkError(t: Throwable) {
        context.showToast(context.getString(R.string.check_internet_connection_error), LENGTH_LONG)
    }

    override fun showProtocolError(errorBean: ErrorBean) = context.showToast(errorBean.message)

    override fun showNonProtocolError(httpException: HttpException) = context.showToast(httpException.message)

    override fun showUnexpectedError(t: Throwable) = context.showToast(t.message)

    override fun showAuthError(httpException: HttpException) = Unit

}