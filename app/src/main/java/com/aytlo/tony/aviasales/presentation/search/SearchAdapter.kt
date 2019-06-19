package com.aytlo.tony.aviasales.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.data.model.City

class SearchAdapter(private val clickListener: (City) -> Unit) : RecyclerView.Adapter<SearchItemHolder>() {

    private val items = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.li_search, parent, false)
        return SearchItemHolder(clickListener, view)
    }

    override fun onBindViewHolder(holder: SearchItemHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    fun showItems(cities: List<City>) {
        items.run {
            clear()
            addAll(cities)
        }
        notifyDataSetChanged()
    }
}