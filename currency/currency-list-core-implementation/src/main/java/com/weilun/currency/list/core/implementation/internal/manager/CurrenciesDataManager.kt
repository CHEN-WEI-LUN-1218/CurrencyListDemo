package com.weilun.currency.list.core.implementation.internal.manager

import com.weilun.core.utils.log.logDebug
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.core.implementation.internal.db.dao.CurrenciesDao
import com.weilun.currency.list.core.implementation.internal.factory.CurrenciesStateFactory
import com.weilun.currency.list.core.implementation.internal.network.CurrenciesNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest

class CurrenciesDataManager(
    private val networkApi: Lazy<CurrenciesNetworkApi>,
    private val currenciesDao: Lazy<CurrenciesDao>,
    private val factory: Lazy<CurrenciesStateFactory>,
) {
    private val currentSortingStateFlow: MutableStateFlow<CurrenciesSorting> by lazy {
        MutableStateFlow(CurrenciesSorting.DESC)
    }

    fun whenCurrenciesDataStateChanged(): Flow<CurrencyState> {
        return combine(
            currenciesDao.value.getCurrencies(),
            currentSortingStateFlow
        ) { currencies, sorting ->
            currencies to sorting
        }.mapLatest { currenciesAndSorting ->
            return@mapLatest factory.value.fromCurrencyEntity(currenciesAndSorting.first, currenciesAndSorting.second)
        }
    }

    suspend fun updateCurrencies() {
        val list = networkApi.value.provideCurrencies()
        currenciesDao.value.insertCurrencies(list)
        logDebug("Currency updated")
    }

    suspend fun sortCurrencies(currenciesSorting: CurrenciesSorting) {
        currentSortingStateFlow.emit(currenciesSorting)
    }
}