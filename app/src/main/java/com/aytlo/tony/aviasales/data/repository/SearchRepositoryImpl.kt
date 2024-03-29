package com.aytlo.tony.aviasales.data.repository

import com.aytlo.tony.aviasales.data.model.Cities
import com.aytlo.tony.aviasales.data.source.remote.AviasalesRepository
import java.util.*

class SearchRepositoryImpl(private val aviasalesRepository: AviasalesRepository) : SearchRepository {
    override suspend fun getCities(term: String): Cities =
        aviasalesRepository.getCities(term, Locale.getDefault().language)
}