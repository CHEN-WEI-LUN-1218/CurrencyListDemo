package com.weilun.core.base.di

import com.weilun.core.base.app.AppLogicRunner
import com.weilun.core.base.app.ApplicationInitializer
import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.currency.list.core.implementation.applogic.AppCurrencyDataAppLogic
import org.koin.dsl.module

object CoreBaseModule {
    val module by lazy {
        module {
            single<AppLogicRunner> {
                AppLogicRunner(
                    setOf(
                        get<AppCurrencyDataAppLogic>()
                    ),
                    get<DispatchersProvider>()
                )
            }

            single<ApplicationInitializer> {
                ApplicationInitializer(lazy(LazyThreadSafetyMode.SYNCHRONIZED) { get<AppLogicRunner>() })
            }
        }
    }
}
