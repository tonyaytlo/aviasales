package com.aytlo.tony.aviasales.presentation.flight

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.presentation.base.fragment.BaseFragment
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
        viewModel.prepareScreen()
    }

    private fun setListeners() {
        btnSearch.setOnClickListener { }
        etDeparturePoint.setOnClickListener { }
        etArrivalPoint.setOnClickListener { }
    }

    private fun observeData() {
        viewModel.let { vm ->
            vm.validationState.observe(this, Observer { btnSearch.isEnabled = it })
            vm.arrival.observe(this, Observer { etArrivalPoint.setText(it) })
            vm.departure.observe(this, Observer { etDeparturePoint.setText(it) })
        }
    }
}