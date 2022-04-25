package com.weilun.currency.list.ui.implementation.model

import com.weilun.currency.list.core.bridge.model.CurrencyState

data class CurrencyListViewState(
    val currencyState: CurrencyState
) {
    companion object {
        val DEFAULT = CurrencyListViewState(CurrencyState.DEFAULT)
    }
}