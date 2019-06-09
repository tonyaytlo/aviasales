package com.aytlo.tony.aviasales.data.repository

import com.aytlo.tony.aviasales.data.model.Cities

interface SearchRepository {
    suspend fun getCities(term: String): Cities
}