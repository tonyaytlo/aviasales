package com.aytlo.tony.aviasales.presentation.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.extension.android.widget.addTextChangedListener
import com.aytlo.tony.aviasales.presentation.base.fragment.BaseFragment
import com.aytlo.tony.aviasales.presentation.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fr_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by viewModel {
        parametersOf(
            arguments?.getBoolean(ARG_IS_DEPARTURE) ?: false
        )
    }
    private val adapter by lazy { SearchAdapter(viewModel::onItemClick) }

    override fun getLayout() = R.layout.fr_search

    override fun obtainViewModel(): BaseViewModel = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initRecycler()
        observeData()
    }

    private fun setListeners() {
        etCity.addTextChangedListener {
            onTextChanged { text, _, _, _ ->
                viewModel.onQueryTextChanged(
                    text.toString()
                )
            }
        }
    }

    private fun initRecycler() {
        rvCities.run {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = this@SearchFragment.adapter
        }
    }

    private fun observeData() {
        viewModel.let { vm ->
            vm.cities.observe(this, Observer { adapter.showItems(it) })
            vm.exit.observe(this, Observer { activity?.onBackPressed() })
        }
    }

    companion object {
        const val ARG_IS_DEPARTURE = "is_departure"

        fun makeFragment(isDeparture: Boolean) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_DEPARTURE, isDeparture)
                }
            }
    }
}