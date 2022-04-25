package com.weilun.currencylistdemo.di

import android.content.Context
import com.weilun.core.analytics.implementation.di.AnalyticsModule
import com.weilun.core.base.di.CoreBaseModule
import com.weilun.core.utils.di.CoreUtilsModule
import com.weilun.currency.list.core.implementation.di.CurrencyDataModule
import com.weilun.currency.list.ui.implementation.di.CurrencyUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class AppModulesInjector {
    val defaultModules by lazy {
        listOf(
            CoreBaseModule.module,
            CoreUtilsModule.module,
            AnalyticsModule.module,
            CurrencyDataModule.module,
            CurrencyUiModule.module
        )
    }

    fun injectProd(appContext: Context) {
        startKoin {
            androidContext(appContext)
            fragmentFactory()
            modules(defaultModules)
        }
    }
}

