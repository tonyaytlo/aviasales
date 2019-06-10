package com.aytlo.tony.aviasales.util

import android.animation.TypeEvaluator
import com.google.android.gms.maps.model.LatLng


class RouteEvaluator : TypeEvaluator<LatLng> {

    override fun evaluate(t: Float, startPoint: LatLng, endPoint: LatLng): LatLng {
        val lat = startPoint.latitude + t * (endPoint.latitude - startPoint.latitude)
        var lngDelta = endPoint.longitude - startPoint.longitude

        if (Math.abs(lngDelta) > THREE_HUNDRED_SIXTY / 2) {
            lngDelta -= Math.signum(lngDelta) * THREE_HUNDRED_SIXTY
        }
        val lng = lngDelta * t + startPoint.longitude

        return LatLng(lat, lng)
    }

    companion object {
        private const val THREE_HUNDRED_SIXTY = 360
    }
}