package com.aytlo.tony.aviasales.di

import com.aytlo.tony.aviasales.presentation.flight.FlightViewModel
import com.aytlo.tony.aviasales.presentation.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FlightViewModel(get())
    }
    viewModel { (isDeparture: Boolean) ->
        SearchViewModel(get(), get(), isDeparture)
    }
}