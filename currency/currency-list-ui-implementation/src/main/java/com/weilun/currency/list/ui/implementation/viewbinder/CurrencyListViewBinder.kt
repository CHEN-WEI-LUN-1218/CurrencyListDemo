package com.weilun.currency.list.ui.implementation.viewbinder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.adapter.CurrencyListAdapter
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class CurrencyListViewBinder(
    private val context: Context,
    private val rootView: View,
    private val adapter: CurrencyListAdapter
) {

    internal val fabSort: ExtendedFloatingActionButton by lazy { rootView.findViewById(R.id.fabSort) }
    internal val rvCurrencies: RecyclerView by lazy { rootView.findViewById(R.id.rvCurrencies) }

    private val viewActionState: MutableSharedFlow<CurrencyListViewAction> by lazy {
        MutableSharedFlow(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    internal var currentSorting: CurrenciesSorting = CurrenciesSorting.ASC

    fun initView() {
        rvCurrencies.layoutManager = LinearLayoutManager(context)
        rvCurrencies.adapter = adapter
        fabSort.setOnClickListener {
            updateSorting()
        }
    }

    fun bindViewState(currencyListViewState: CurrencyListViewState) {
        currentSorting = currencyListViewState.currencyState.sortingState
        updateSortingLabel()
        adapter.submitList(currencyListViewState.currencyState.currencies)
    }

    fun whenViewActionEmitted(): SharedFlow<CurrencyListViewAction> {
        return viewActionState.asSharedFlow()
    }

    internal fun updateSorting() {
        if (currentSorting == CurrenciesSorting.DESC) {
            viewActionState.tryEmit(CurrencyListViewAction.Sort(CurrenciesSorting.ASC))
        } else {
            viewActionState.tryEmit(CurrencyListViewAction.Sort(CurrenciesSorting.DESC))
        }
    }

    internal fun updateSortingLabel() {
        when (currentSorting) {
            CurrenciesSorting.ASC -> {
                fabSort.text = "DESC"
            }
            CurrenciesSorting.DESC -> {
                fabSort.text = "ASC"

            }
        }
    }
}