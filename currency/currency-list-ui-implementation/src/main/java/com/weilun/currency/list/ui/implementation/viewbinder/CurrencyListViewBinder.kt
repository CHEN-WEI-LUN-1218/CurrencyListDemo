package com.weilun.currency.list.ui.implementation.viewbinder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.adapter.CurrencyListAdapter
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewState
import kotlinx.coroutines.flow.MutableSharedFlow

class CurrencyListViewBinder(
    private val context: Context,
    private val rootView: View,
    private val adapter: CurrencyListAdapter
) {

    internal val fabSort: FloatingActionButton by lazy { rootView.findViewById(R.id.fabSort) }
    internal val rvCurrencies: RecyclerView by lazy { rootView.findViewById(R.id.rvCurrencies) }

    private val viewActionState: MutableSharedFlow<CurrencyListViewAction> by lazy { MutableSharedFlow() }

    internal var currentSorting: CurrenciesSorting = CurrenciesSorting.ASC

    fun initView() {
        rvCurrencies.layoutManager = LinearLayoutManager(context)
        rvCurrencies.adapter = adapter
        fabSort.setOnClickListener {
            updateSorting()
        }
        adapter.setItemSelectedFlow(viewActionState)
    }

    fun bindViewState(currencyListViewState: CurrencyListViewState) {
        currentSorting = currencyListViewState.currencyState.sortingState
        adapter.submitList(currencyListViewState.currencyState.currencies)
    }

    fun whenViewActionEmitted(): MutableSharedFlow<CurrencyListViewAction> {
        return viewActionState
    }

    internal fun updateSorting() {
        if (currentSorting == CurrenciesSorting.DESC) {
            currentSorting = CurrenciesSorting.ASC
            viewActionState.tryEmit(CurrencyListViewAction.Sort(currentSorting))
        } else {
            currentSorting = CurrenciesSorting.DESC
            viewActionState.tryEmit(CurrencyListViewAction.Sort(currentSorting))
        }
    }
}