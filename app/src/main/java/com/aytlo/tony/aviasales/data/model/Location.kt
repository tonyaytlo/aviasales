package com.aytlo.tony.aviasales.data.model

import com.google.android.gms.maps.model.LatLng

data class Location(
    val lat: Double,
    val lon: Double
) {
    fun toLatLng() = LatLng(lat, lon)
}