package com.aytlo.tony.aviasales.presentation.map

import com.aytlo.tony.aviasales.data.model.City
import com.aytlo.tony.aviasales.data.repository.FlightRepository
import com.aytlo.tony.aviasales.presentation.base.viewmodel.BaseViewModel
import com.aytlo.tony.aviasales.util.SingleLiveEvent

class MapViewModel(private val flightRepository: FlightRepository) : BaseViewModel() {

    val eventStartAnimation = SingleLiveEvent<Pair<City, City>>()

    fun onMapReady() {
        val fligh = flightRepository.getFlight()
        val departure = fligh.departurePoint
        val arrival = fligh.arrivalPoint
        if (departure == null || arrival == null) {
            throw IllegalArgumentException()
        }
        eventStartAnimation.value = departure to arrival
    }
}