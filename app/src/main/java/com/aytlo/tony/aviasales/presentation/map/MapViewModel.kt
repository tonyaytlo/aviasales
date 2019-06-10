package com.aytlo.tony.aviasales.presentation.map

import com.aytlo.tony.aviasales.data.model.City
import com.aytlo.tony.aviasales.data.repository.FlightRepository
import com.aytlo.tony.aviasales.presentation.base.viewmodel.BaseViewModel
import com.aytlo.tony.aviasales.util.SingleLiveEvent

class MapViewModel(private val flightRepository: FlightRepository) : BaseViewModel() {

    val eventStartAnimation = SingleLiveEvent<Pair<City, City>>()

    fun onMapReady() {
        val flight = flightRepository.getFlight()
        val departure = flight.departurePoint
        val arrival = flight.arrivalPoint

        if (departure == null || arrival == null) {
            throw IllegalArgumentException("flight is not complete")
        }
        eventStartAnimation.value = departure to arrival
    }
}