package com.aytlo.tony.aviasales

import android.app.Application
import com.aytlo.tony.aviasales.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AviasalesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoinDi()
    }

    private fun setupKoinDi() {
        startKoin {
            modules(listOf(appModule))
            androidContext(this@AviasalesApp)
        }
    }
}