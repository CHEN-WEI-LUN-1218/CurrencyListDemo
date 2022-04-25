package com.weilun.core.analytics.implementation.di

import com.weilun.core.analytics.bridge.AnalyticsKit
import com.weilun.core.analytics.implementation.AppAnalyticsKit
import org.koin.dsl.module

object AnalyticsModule {
    val module by lazy {
        module {
            factory<AnalyticsKit> { AppAnalyticsKit() }
        }
    }
}
