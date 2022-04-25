package com.weilun.currency.list.ui.implementation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.core.utils.log.logDebug
import com.weilun.currency.list.core.bridge.CurrencyDataRepository
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewState
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val repository: Lazy<CurrencyDataRepository>,
    private val dispatchersProvider: Lazy<DispatchersProvider>
) : ViewModel() {

    //Cancel subscription to release Room InvalidationObserver
    private val superVisorJob by lazy { SupervisorJob() }
    private val currencyListViewStateFlow: MutableStateFlow<CurrencyListViewState> by lazy {
        MutableStateFlow(CurrencyListViewState.DEFAULT)
    }

    fun onResume() {
        viewModelScope.launch(dispatchersProvider.value.io + superVisorJob) {
            repository.value.getCurrencies().collectLatest {
                currencyListViewStateFlow.tryEmit(CurrencyListViewState(it))
            }
        }
    }

    fun onPause() {
        superVisorJob.cancel()
    }

    fun handleViewAction(viewAction: CurrencyListViewAction) {
        when (viewAction) {
            is CurrencyListViewAction.Sort -> {
                updateSorting(viewAction.sorting)
            }
            is CurrencyListViewAction.SelectItem -> {
                val data = viewAction.currencyData
                logDebug("Item selected -> Id : ${data.id}, Name : ${data.name}, Symbol : ${data.symbol}")
            }
        }
    }

    fun updateSorting(sorting: CurrenciesSorting) {
        viewModelScope.launch(dispatchersProvider.value.io + superVisorJob) {
            repository.value.sortCurrencies(sorting)
        }
    }

    fun whenCurrencyDataChanged(): Flow<CurrencyListViewState> {
        return currencyListViewStateFlow
    }
}