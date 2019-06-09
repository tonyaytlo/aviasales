package com.aytlo.tony.aviasales.data.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("fullname") val detailedName: String,
    @SerializedName("city") val name: String,
    val location: Location
)