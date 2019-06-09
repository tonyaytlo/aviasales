package com.aytlo.tony.aviasales.data.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("fullname") val detailedName: String,
    val city: String,
    val location: Location
)