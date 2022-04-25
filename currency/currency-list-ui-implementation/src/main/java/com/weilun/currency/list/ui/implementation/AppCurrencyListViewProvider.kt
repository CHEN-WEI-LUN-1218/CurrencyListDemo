package com.weilun.currency.list.ui.implementation

import androidx.fragment.app.Fragment
import com.weilun.currency.list.ui.bridge.CurrencyListViewProvider

class AppCurrencyListViewProvider : CurrencyListViewProvider<Fragment> {
    override fun provideCurrencyListView(): Fragment {
        return CurrencyListFragment.getInstance()
    }
}