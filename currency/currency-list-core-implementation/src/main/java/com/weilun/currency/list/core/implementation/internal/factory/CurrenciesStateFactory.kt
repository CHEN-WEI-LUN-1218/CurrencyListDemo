package com.weilun.currency.list.core.implementation.internal.factory

import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import kotlinx.coroutines.flow.combine

class CurrenciesStateFactory {
    fun fromCurrencyEntity(currencies: List<Currency>, sorting: CurrenciesSorting): CurrencyState {
        return CurrencyState(
            when (sorting) {
                CurrenciesSorting.DESC -> currencies.sortedByDescending { it.name }
                CurrenciesSorting.ASC -> currencies.sortedBy { it.name }
            }.map { CurrencyData(it.id, it.name, it.symbol) },
            sorting
        )
    }
}