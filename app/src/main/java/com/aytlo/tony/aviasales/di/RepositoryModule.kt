package com.aytlo.tony.aviasales.di

import com.aytlo.tony.aviasales.data.repository.FlightRepository
import com.aytlo.tony.aviasales.data.repository.FlightRepositoryImpl
import com.aytlo.tony.aviasales.data.repository.SearchRepository
import com.aytlo.tony.aviasales.data.repository.SearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        FlightRepositoryImpl() as FlightRepository
    }
    single {
        SearchRepositoryImpl(get()) as SearchRepository
    }
}