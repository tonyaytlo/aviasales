package com.aytlo.tony.aviasales.data.repository

import com.aytlo.tony.aviasales.data.model.Flight

class FlightRepositoryImpl : FlightRepository {

    private var flight = Flight()

    override fun getFlight(): Flight = flight

    override fun saveFlight(flight: Flight) {
        this.flight = flight
    }
}