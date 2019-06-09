package com.aytlo.tony.aviasales.presentation.flight

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.presentation.base.fragment.BaseFragment
import com.aytlo.tony.aviasales.presentation.search.SearchActivity
import kotlinx.android.synthetic.main.fr_flight.*
import org.koin.android.viewmodel.ext.android.viewModel

class FlightFragment : BaseFragment() {

    private val viewModel: FlightViewModel by viewModel()

    override fun getLayout() = R.layout.fr_flight

    override fun obtainViewModel() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.prepareScreen()
    }

    private fun setListeners() {
        btnSearch.setOnClickListener { }
        tvDeparturePoint.setOnClickListener { openSearch(true) }
        tvArrivalPoint.setOnClickListener { openSearch(false) }
    }

    private fun openSearch(isDeparture: Boolean) {
        startActivity(SearchActivity.makeIntent(requireContext(), isDeparture))
    }

    private fun observeData() {
        viewModel.let { vm ->
            vm.validationState.observe(this, Observer { btnSearch.isEnabled = it })
            vm.arrival.observe(this, Observer { tvArrivalPoint.text = it })
            vm.departure.observe(this, Observer { tvDeparturePoint.text = it })
        }
    }
}