package com.aytlo.tony.aviasales.presentation.base.error

import androidx.lifecycle.Observer
import com.aytlo.tony.aviasales.data.model.ErrorBean
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ErrorHandler(
    private val errorView: ErrorView,
    private val gson: Gson
) : Observer<Throwable> {

    override fun onChanged(t: Throwable?) {
        t?.let { handleError(it) }
    }

    private fun handleError(t: Throwable) {
        when {
            t is HttpException -> handleHttpException(t)
            NETWORK_EXCEPTIONS.contains(t.javaClass) -> errorView.showNetworkError(t)
            else -> errorView.showUnexpectedError(t)
        }
    }

    private fun handleHttpException(e: HttpException) {
        if (e.code() == AUTH_ERROR_HTTP_CODE) {
            errorView.showAuthError(e)
        } else {
            try {
                val errorBody = e.response()?.errorBody()?.string()
                val errorBean = gson.fromJson(errorBody, ErrorBean::class.java)
                if (errorBean != null) {
                    errorView.showProtocolError(errorBean)
                } else {
                    errorView.showNonProtocolError(e)
                }
            } catch (e1: IOException) {
                errorView.showNonProtocolError(e)
            } catch (e1: IllegalStateException) {
                errorView.showNonProtocolError(e)
            } catch (e1: JsonSyntaxException) {
                errorView.showNonProtocolError(e)
            }
        }
    }

    companion object {
        private const val AUTH_ERROR_HTTP_CODE = 401

        private val NETWORK_EXCEPTIONS = listOf<Class<*>>(
            UnknownHostException::class.java,
            SocketTimeoutException::class.java,
            ConnectException::class.java
        )
    }
}