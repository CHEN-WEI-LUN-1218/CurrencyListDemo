package com.weilun.currency.list.core.implementation.di

import android.content.Context
import com.weilun.currency.list.core.bridge.CurrencyDataRepository
import com.weilun.currency.list.core.implementation.AppCurrencyDataRepository
import com.weilun.currency.list.core.implementation.applogic.AppCurrencyDataAppLogic
import com.weilun.currency.list.core.implementation.internal.db.AppCurrencyDatabase
import com.weilun.currency.list.core.implementation.internal.db.AppCurrencyDatabaseFactory
import com.weilun.currency.list.core.implementation.internal.factory.CurrenciesStateFactory
import com.weilun.currency.list.core.implementation.internal.manager.CurrenciesDataManager
import com.weilun.currency.list.core.implementation.internal.network.CurrenciesNetworkApi
import com.weilun.currency.list.core.implementation.internal.network.MockCurrenciesNetworkApi
import org.koin.dsl.module

object CurrencyDataModule {
    val module by lazy {
        module {
            factory<CurrenciesNetworkApi> { MockCurrenciesNetworkApi() }
            factory { CurrenciesStateFactory() }
            single<AppCurrencyDatabase> { AppCurrencyDatabaseFactory.createDb(get<Context>()) }
            single {
                CurrenciesDataManager(
                    networkApi = lazy { get() },
                    currenciesDao = lazy { get<AppCurrencyDatabase>().currenciesDao() },
                    factory = lazy { get() }
                )
            }

            single<AppCurrencyDataAppLogic> {
                AppCurrencyDataAppLogic(
                    repository = lazy { get() }
                )
            }

            single<CurrencyDataRepository> {
                AppCurrencyDataRepository(
                    currenciesDataManager = lazy { get() }
                )
            }

        }
    }
}