package com.aytlo.tony.aviasales.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aytlo.tony.aviasales.data.model.City
import com.aytlo.tony.aviasales.data.repository.FlightRepository
import com.aytlo.tony.aviasales.data.repository.SearchRepository
import com.aytlo.tony.aviasales.presentation.base.viewmodel.BaseViewModel
import com.aytlo.tony.aviasales.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val flightRepository: FlightRepository,
    private val isDeparture: Boolean
) : BaseViewModel() {

    val cities = MutableLiveData<List<City>>()
    val exit = SingleLiveEvent<Nothing>()

    private val broadcastText = ConflatedBroadcastChannel<String>()
    private var searchJob: Job? = null

    init {
        setQueryTextChangeObserver()
    }

    private fun setQueryTextChangeObserver() {
        viewModelScope.launch(Dispatchers.Main) {
            broadcastText.consumeEach {
                delay(SMALL_DELAY)
                if (searchJob?.isActive == true) {
                    searchJob?.cancel()
                }
                searchJob = launchLoadingErrorJob {
                    cities.value = searchRepository.getCities(it).cities
                }
            }
        }
    }

    fun onQueryTextChanged(query: String) {
        broadcastText.sendBlocking(query)
    }

    fun onItemClick(city: City) {
        flightRepository.saveFlight(flightRepository.getFlight().apply {
            if (isDeparture) {
                departurePoint = city
            } else {
                arrivalPoint = city
            }
        })
        exit.call()
    }

    companion object {
        private const val SMALL_DELAY = 300L
    }
}