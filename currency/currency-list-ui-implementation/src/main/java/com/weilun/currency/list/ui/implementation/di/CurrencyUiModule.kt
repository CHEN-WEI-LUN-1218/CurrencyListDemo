package com.weilun.currency.list.ui.implementation.di

import androidx.fragment.app.Fragment
import com.weilun.currency.list.ui.bridge.CurrencyListViewProvider
import com.weilun.currency.list.ui.implementation.AppCurrencyListViewProvider
import com.weilun.currency.list.ui.implementation.viewholder.CurrencyViewItemInteractor
import com.weilun.currency.list.ui.implementation.viewmodel.CurrencyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CurrencyUiModule {
    val module by lazy {
        module {
            single<CurrencyViewItemInteractor> { CurrencyViewItemInteractor() }
            single<CurrencyListViewProvider<Fragment>> { AppCurrencyListViewProvider() }
            viewModel {
                CurrencyListViewModel(
                    repository = lazy { get() },
                    dispatchersProvider = lazy { get() }
                )
            }
        }
    }
}