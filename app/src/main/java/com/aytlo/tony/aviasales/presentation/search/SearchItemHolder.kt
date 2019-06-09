package com.aytlo.tony.aviasales.presentation.search

import android.view.View
import com.aytlo.tony.aviasales.data.model.City
import com.aytlo.tony.aviasales.presentation.base.adapter.BaseRViewHolder
import kotlinx.android.synthetic.main.li_search.*


class SearchItemHolder(
    clickListener: (City) -> Unit,
    itemView: View
) : BaseRViewHolder(itemView) {

    private lateinit var city: City

    init {
        itemView.setOnClickListener { clickListener(city) }
    }

    fun bind(city: City) {
        this.city = city
        tvTitle.text = city.name
        tvSubTitle.text = city.detailedName
    }
}