package com.weilun.currency.list.ui.implementation.model

import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyData

sealed class CurrencyListViewAction {
    class Sort(val sorting: CurrenciesSorting) : CurrencyListViewAction()

    //Could consider to only parse id/name/symbol
    class SelectItem(val currencyData: CurrencyData) : CurrencyListViewAction()
}