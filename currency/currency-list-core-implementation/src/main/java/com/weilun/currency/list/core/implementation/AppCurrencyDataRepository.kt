package com.weilun.currency.list.core.implementation

import com.weilun.currency.list.core.bridge.CurrencyDataRepository
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.core.implementation.internal.db.dao.CurrenciesDao
import com.weilun.currency.list.core.implementation.internal.manager.CurrenciesDataManager
import com.weilun.currency.list.core.implementation.internal.network.CurrenciesNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class AppCurrencyDataRepository(
    private val currenciesDataManager: Lazy<CurrenciesDataManager>
) : CurrencyDataRepository {

    override suspend fun init() {
        currenciesDataManager.value.updateCurrencies()
    }

    override suspend fun sortCurrencies(sorting: CurrenciesSorting) {
        currenciesDataManager.value.sortCurrencies(sorting)
    }

    override fun getCurrencies(): Flow<CurrencyState> {
        return currenciesDataManager.value.whenCurrenciesDataStateChanged()
    }
}