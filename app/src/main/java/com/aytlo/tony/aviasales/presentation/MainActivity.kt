package com.aytlo.tony.aviasales.presentation

import com.aytlo.tony.aviasales.presentation.base.activity.SingleFragmentActivity
import com.aytlo.tony.aviasales.presentation.flight.FlightFragment

class MainActivity : SingleFragmentActivity() {

    override fun createFragment() = FlightFragment()
}
