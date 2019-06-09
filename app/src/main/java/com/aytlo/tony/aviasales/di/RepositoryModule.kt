package com.aytlo.tony.aviasales.di

import com.aytlo.tony.aviasales.data.repository.FlightRepository
import com.aytlo.tony.aviasales.data.repository.FlightRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        FlightRepositoryImpl() as FlightRepository
    }
}