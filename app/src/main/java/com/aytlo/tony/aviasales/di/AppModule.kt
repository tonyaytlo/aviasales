package com.aytlo.tony.aviasales.di

import com.aytlo.tony.aviasales.data.source.remote.AviasalesRepository
import com.aytlo.tony.aviasales.data.source.remote.provider.UrlProvider
import com.aytlo.tony.aviasales.data.source.remote.provider.UrlProviderImpl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        OkHttpClient.Builder().build()
    }

    single {
        UrlProviderImpl() as UrlProvider
    }

    single {
        GsonBuilder().create()
    }

    single {
        Retrofit.Builder()
            .baseUrl((get() as UrlProvider).url)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(AviasalesRepository::class.java)
    }
}