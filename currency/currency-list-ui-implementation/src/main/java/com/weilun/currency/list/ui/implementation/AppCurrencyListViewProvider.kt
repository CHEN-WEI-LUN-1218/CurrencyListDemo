package com.weilun.currency.list.ui.implementation

import androidx.fragment.app.Fragment
import com.weilun.currency.list.ui.bridge.CurrencyListViewProvider

class AppCurrencyListViewProvider : CurrencyListViewProvider<Fragment> {
    private val fragment by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        CurrencyListFragment.getInstance()
    }

    override fun provideCurrencyListView(): Fragment {
        return fragment
    }
}