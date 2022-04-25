package com.weilun.currencylistdemo.application

import android.app.Application
import android.os.SystemClock
import android.util.Log
import com.weilun.core.utils.log.DebugTree
import com.weilun.core.analytics.bridge.AnalyticsKit
import com.weilun.core.base.app.ApplicationInitializer
import com.weilun.core.base.app.KoinComponentInjectable
import com.weilun.core.base.metrics.AppStartAnalyticsConstant
import com.weilun.core.base.metrics.AppStartTracer
import com.weilun.core.utils.log.ReleaseTree
import com.weilun.core.utils.time.toTimeInMillis
import com.weilun.currencylistdemo.BuildConfig
import com.weilun.currencylistdemo.di.AppModulesInjector
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import timber.log.Timber
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource
import kotlin.time.toDuration

/**
 * This is the application for iAuditor it holds any
 * global methods or variables required.
 *
 * @author Joshua Wilson
 */
@ExperimentalTime
open class DemoApplication : Application(), KoinComponent, KoinComponentInjectable {
    //Use system clock to see how soon processor start to executed first line of our application
    private val dexLoadTime = SystemClock.currentThreadTimeMillis()
            .toDouble()
            .toDuration(DurationUnit.MILLISECONDS)
            .also {
                AppStartTracer.dexLoad = it
            }

    private val singleTracer = TimeSource.Monotonic.markNow()
    private val analyticsKit: AnalyticsKit by inject()
    private val applicationInitializer: ApplicationInitializer by inject()
    private val appModulesInjector: AppModulesInjector by lazy { AppModulesInjector() }

    override fun injectAppComponent() {
        injectProd()
    }

    override fun onCreate() = TimeSource.Monotonic.markNow().also {
        super.onCreate()
        injectAppComponent()
        if (BuildConfig.DEBUG) {
            Log.d("BaoTest", "Plant DebugTree")
            Timber.plant(DebugTree())
        } else {
            Log.d("BaoTest", "Plant releaseTree")
            Timber.plant(ReleaseTree())
        }
        //Execute app initializer
        //Order of execution should be after Analytics & ApplicationPreferences is loaded as one of the use case depends on these services
        applicationInitializer.initApp()
    }.let {
        AppStartTracer.applicationOnCreate = it.elapsedNow()
        val appStartDuration = AppStartTracer.sumUpApplicationStartDuration().toTimeInMillis()
        analyticsKit.trackEvent(AppStartAnalyticsConstant.EVENT_APPLICATION_START,
                mapOf(AppStartAnalyticsConstant.PROPERTY_DURATION to appStartDuration,
                        AppStartAnalyticsConstant.PROPERTY_CREATE to AppStartTracer.applicationOnCreate.toTimeInMillis(),
                        AppStartAnalyticsConstant.PROPERTY_ATTR_INIT to AppStartTracer.applicationClassAttrInit.toTimeInMillis()))
        AppStartTracer.initActivityCreateStart = TimeSource.Monotonic.markNow()
    }

    private fun injectProd() {
        appModulesInjector.injectProd(this)
    }

    private val applicationAttrInit = singleTracer.elapsedNow().also {
        AppStartTracer.applicationClassAttrInit = it
    }
}
