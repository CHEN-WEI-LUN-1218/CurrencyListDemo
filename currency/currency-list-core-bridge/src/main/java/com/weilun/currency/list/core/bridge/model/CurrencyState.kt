package com.weilun.currency.list.core.bridge.model

data class CurrencyState(
    val currencies: List<CurrencyData>,
    val sortingState: CurrenciesSorting
) {
    companion object {
        val DEFAULT = CurrencyState(emptyList(), CurrenciesSorting.DESC)
    }
}