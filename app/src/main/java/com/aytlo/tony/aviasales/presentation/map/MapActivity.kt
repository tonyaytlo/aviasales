package com.aytlo.tony.aviasales.presentation.map

import android.content.Context
import android.content.Intent
import com.aytlo.tony.aviasales.presentation.base.activity.SingleFragmentActivity

class MapActivity : SingleFragmentActivity() {

    override fun createFragment() = MapFragment()

    companion object {
        fun makeIntent(context: Context) = Intent(context, MapActivity::class.java)
    }
}