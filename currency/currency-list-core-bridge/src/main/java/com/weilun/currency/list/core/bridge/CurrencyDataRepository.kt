package com.weilun.currency.list.core.bridge

import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyState
import kotlinx.coroutines.flow.Flow

interface CurrencyDataRepository {
    suspend fun init()
    suspend fun sortCurrencies(sorting: CurrenciesSorting)
    fun getCurrencies(): Flow<CurrencyState>
}