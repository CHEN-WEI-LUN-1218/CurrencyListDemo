package com.weilun.currency.list.ui.bridge

interface CurrencyListViewProvider<T> {
    fun provideCurrencyListView(): T
}