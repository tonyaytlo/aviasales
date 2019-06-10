package com.aytlo.tony.aviasales.presentation.search

import android.content.Context
import android.content.Intent
import com.aytlo.tony.aviasales.presentation.base.activity.SingleFragmentActivity
import com.aytlo.tony.aviasales.presentation.search.SearchFragment.Companion.ARG_IS_DEPARTURE

class SearchActivity : SingleFragmentActivity() {

    override fun createFragment() =
        SearchFragment.makeFragment(intent?.extras?.getBoolean(ARG_IS_DEPARTURE, false) ?: false)

    companion object {
        fun makeIntent(context: Context, isDeparture: Boolean) =
            Intent(context, SearchActivity::class.java)
                .apply {
                    putExtra(ARG_IS_DEPARTURE, isDeparture)
                }
    }
}