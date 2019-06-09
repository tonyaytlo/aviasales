package com.aytlo.tony.aviasales.presentation.flight

import androidx.lifecycle.MutableLiveData
import com.aytlo.tony.aviasales.data.repository.FlightRepository
import com.aytlo.tony.aviasales.presentation.base.viewmodel.BaseViewModel

class FlightViewModel(private val flightRepository: FlightRepository) : BaseViewModel() {

    val departure = MutableLiveData<String>()
    val arrival = MutableLiveData<String>()
    val validationState = MutableLiveData<Boolean>()


    fun prepareScreen() {
        val flight = flightRepository.getFlight()
        val departureCity = flight?.departurePoint?.city ?: EMPTY
        val arrivalCity = flight?.arrivalPoint?.city ?: EMPTY

        arrival.value = arrivalCity
        departure.value = departureCity
        validationState.value = departureCity.isNotEmpty() && arrivalCity.isNotEmpty()
    }

    companion object {
        private const val EMPTY = ""
    }
}