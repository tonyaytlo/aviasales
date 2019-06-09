package com.aytlo.tony.aviasales.data.repository

import com.aytlo.tony.aviasales.data.model.Flight

interface FlightRepository {

    fun getFlight(): Flight

    fun saveFlight(flight: Flight)
}