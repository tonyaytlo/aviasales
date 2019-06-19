package com.aytlo.tony.aviasales.presentation.map

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.lifecycle.Observer
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.data.model.City
import com.aytlo.tony.aviasales.presentation.base.fragment.BaseFragment
import com.aytlo.tony.aviasales.util.MarkerBitmapBuilder
import com.aytlo.tony.aviasales.util.RouteEvaluator
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import org.koin.android.ext.android.get


class MapFragment : BaseFragment(), OnMapReadyCallback {

    private val viewModel: MapViewModel = get()
    private var currentAnimationTime = 0L
    private lateinit var map: GoogleMap
    private lateinit var planeMarker: Marker
    private var planeAnimator: ObjectAnimator? = null

    override fun getLayout() = R.layout.fr_map

    override fun obtainViewModel() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            currentAnimationTime = savedInstanceState.getLong(ARG_CURRENT_ANIMATION_TIME)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        observeData()
    }

    private fun observeData() {
        viewModel.eventStartAnimation.observe(this, Observer { startScene(it) })
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style))
        viewModel.onMapReady()
    }

    private fun startScene(flight: Pair<City, City>) {
        val departure = flight.first.location.toLatLng()
        val arrival = flight.second.location.toLatLng()

        moveCamera(departure, arrival)
        addPointsMarker(flight.first, flight.second)
        drawPolyline(departure, arrival)
        addPlaneMarker(departure, arrival)
        animatePlane(departure, arrival)
    }

    private fun addPlaneMarker(departure: LatLng, arrival: LatLng) {
        planeMarker = map.addMarker(
            MarkerOptions()
                .position(departure)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane))
                .rotation(SphericalUtil.computeHeading(departure, arrival).toFloat())
                .anchor(ANCHOR, ANCHOR)
        )
    }

    private fun animatePlane(departure: LatLng, arrival: LatLng) {
        planeAnimator = ObjectAnimator
            .ofObject(this, "animatedLatLng", RouteEvaluator(), departure, arrival).apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = ANIMATION_DURATION
                currentPlayTime = currentAnimationTime
                start()
            }
    }

    fun setAnimatedLatLng(endLatLng: LatLng) {
        planeMarker.position = endLatLng
    }

    private fun drawPolyline(departure: LatLng, arrival: LatLng) {
        val polylinePattern: List<PatternItem> = listOf(Dot(), Gap(25f))
        val polyline = PolylineOptions().apply {
            color(Color.GRAY)
            geodesic(false)
            add(departure)
            add(arrival)
            pattern(polylinePattern)
        }

        map.addPolyline(polyline)
    }

    private fun moveCamera(departure: LatLng, arrival: LatLng) {
        val bounds = LatLngBounds.builder()
            .include(departure)
            .include(arrival)
            .build()
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, MAP_POINTS_PADDING))
    }

    private fun addPointsMarker(departure: City, arrival: City) {
        val departureMarker = createPointMarker(departure.location.toLatLng(), departure.name)
        val arrivalMarker = createPointMarker(arrival.location.toLatLng(), arrival.name)

        map.run {
            addMarker(departureMarker)
            addMarker(arrivalMarker)
        }
    }

    private fun createPointMarker(latLng: LatLng, title: String): MarkerOptions {
        return MarkerOptions()
            .alpha(MARKER_ALPHA)
            .icon(BitmapDescriptorFactory.fromBitmap(MarkerBitmapBuilder.buildBitmap(requireContext(), title)))
            .position(latLng)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        planeAnimator?.let {
            outState.putLong(ARG_CURRENT_ANIMATION_TIME, it.currentPlayTime)

        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        planeAnimator?.cancel()
    }

    companion object {
        private const val ANCHOR = 0.5F
        private const val MARKER_ALPHA = 0.95F
        const val ANIMATION_DURATION = 10000L
        const val MAP_POINTS_PADDING = 256
        const val ARG_CURRENT_ANIMATION_TIME = "current_animation_time"
    }
}