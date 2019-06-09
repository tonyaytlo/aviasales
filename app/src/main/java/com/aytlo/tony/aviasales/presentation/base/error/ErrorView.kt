package com.aytlo.tony.aviasales.presentation.base.error

import com.aytlo.tony.aviasales.data.model.ErrorBean
import retrofit2.HttpException

interface ErrorView {

    fun showAuthError(httpException: HttpException)

    fun showProtocolError(errorBean: ErrorBean)

    fun showNonProtocolError(httpException: HttpException)

    fun showNetworkError(t: Throwable)

    fun showUnexpectedError(t: Throwable)
}
