package com.aytlo.tony.aviasales.data.source.remote

import com.aytlo.tony.aviasales.data.model.Cities
import retrofit2.http.GET
import retrofit2.http.Query

interface AviasalesRepository {
    @GET("autocomplete")
    suspend fun getCities(
        @Query("term") term: String,
        @Query("lang") lang: String = "ru"
    ): Cities
}