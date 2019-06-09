package com.aytlo.tony.aviasales.data.repository

import com.aytlo.tony.aviasales.data.model.City
import com.aytlo.tony.aviasales.data.model.Flight
import com.aytlo.tony.aviasales.data.model.Location

class FlightRepositoryImpl : FlightRepository {

    private var flight: Flight? = fakeFlight()

    override fun getFlight(): Flight? = flight

    private fun fakeFlight() = Flight(
        City("Moscow", "Moscow", Location(0.0, 0.0)),
        City("Sain P", "Sain P", Location(0.0, 0.0))
    )
}