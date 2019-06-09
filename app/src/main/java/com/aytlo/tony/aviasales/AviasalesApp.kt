package com.aytlo.tony.aviasales

import android.app.Application
import com.aytlo.tony.aviasales.di.appModule
import com.aytlo.tony.aviasales.di.repositoryModule
import com.aytlo.tony.aviasales.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AviasalesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoinDi()
    }

    private fun setupKoinDi() {
        startKoin {
            modules(listOf(appModule, repositoryModule, viewModelModule))
            androidContext(this@AviasalesApp)
        }
    }
}