package com.weilun.core.base.metrics

import com.weilun.core.utils.time.toTimeInMillis
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark

@ExperimentalTime
object AppStartTracer {
    private const val LONG_START_THRESHOLD = 1500
    var dexLoad = Duration.ZERO
    var applicationClassAttrInit = Duration.ZERO
    var applicationOnCreate = Duration.ZERO

    var initActivityRunning: TimeMark? = null
    var initActivityCreateStart: TimeMark? = null
    var initActivityAttachedBaseContext = Duration.ZERO
    var initActivityClassAttrInit = Duration.ZERO
    var initActivityOnCreate = Duration.ZERO
    var initActivityOnStart = Duration.ZERO
    var initActivityRunningDuration = Duration.ZERO

    var mainActivityCreateStart: TimeMark? = null
    var mainActivityAttachedBaseContext = Duration.ZERO
    var mainActivityClassAttrInit = Duration.ZERO
    var mainActivityOnCreate = Duration.ZERO
    var mainActivityOnStart = Duration.ZERO
    var mainActivityOnResume = Duration.ZERO

    var onBoardingActivityDuration = Duration.ZERO

    /**
     * Duration from App start to see home screen every time when user tap app icon or try start app from recent app list
     */
    fun sumUpUserExpAppStartDuration(): Duration {
        /**
         * In sometimes, Application might be bring up by services and cause the initActivityAttachedBaseContext start counting for a while before user start use App
         * We should not consider this is part of AppStart.
         */
        if (initActivityAttachedBaseContext.toTimeInMillis() > LONG_START_THRESHOLD) {
            initActivityAttachedBaseContext = Duration.ZERO
        }
        return dexLoad + // Apk dex loaded, will be zero if warm start
            applicationOnCreate + // Application onCreate, will be zero if warm start
            applicationClassAttrInit + // Application class attr init, will be zero if warm start
            initActivityAttachedBaseContext + // InitActivity attached, will be zero if warm start
            initActivityClassAttrInit + // InitActivity class attr init, will be zero if hot start
            initActivityOnCreate + // InitActivity onCreate, will be zero if warm start
            initActivityOnStart + // InitActivity onStart, will be zero if hot start
            initActivityRunningDuration + // InitActivity set up duration, will be zero if hot start
            mainActivityClassAttrInit + // MainActivity class attr init duration, will be zero if hot start
            mainActivityAttachedBaseContext + // MainActivity attached , will be zero if hot start
            mainActivityOnCreate + // MainActivity onCreate, will be zero if hot start
            mainActivityOnStart + // MainActivity onStart, will be zero if hot start
            mainActivityOnResume // MainActivity onResume, will be zero if hot start
    }

    /**
     * Duration of Application attach and finish onCreate
     */
    fun sumUpApplicationStartDuration(): Duration {
        return dexLoad + applicationClassAttrInit + applicationOnCreate
    }

    /**
     * Duration of InitActivity attach and finish onCreate
     */
    fun sumUpInitActivityStartDuration(): Duration {
        /**
         * In sometimes, Application might be bring up by services and cause the initActivityAttachedBaseContext start counting for a while before user start use App
         * We should not consider this is part of AppStart.
         */
        if (initActivityAttachedBaseContext.toTimeInMillis() > 3000) {
            initActivityAttachedBaseContext = Duration.ZERO
        }
        return initActivityAttachedBaseContext +
            initActivityClassAttrInit +
            initActivityOnCreate
    }

    /**
     * Duration of MainActivity attach and finish onCreate
     */
    fun sumUpOnBoardingActivityStartDuration(): Duration {
        /**
         * In sometimes, Application might be bring up by services and cause the initActivityAttachedBaseContext start counting for a while before user start use App
         * We should not consider this is part of AppStart.
         */
        if (initActivityAttachedBaseContext.toTimeInMillis() > LONG_START_THRESHOLD) {
            initActivityAttachedBaseContext = Duration.ZERO
        }
        return dexLoad + // Apk dex loaded, will be zero if warm start
            applicationOnCreate + // Application onCreate, will be zero if warm start
            applicationClassAttrInit + // Application class attr init, will be zero if warm start
            initActivityAttachedBaseContext + // InitActivity attached, will be zero if warm start
            initActivityClassAttrInit + // InitActivity class attr init, will be zero if hot start
            initActivityOnCreate + // InitActivity onCreate, will be zero if warm start
            initActivityOnStart + // InitActivity onStart, will be zero if hot start
            initActivityRunningDuration + // InitActivity set up duration, will be zero if hot start
            onBoardingActivityDuration // OnBoardingActivity init duration, will be zero if hot start
    }

    /**
     * Duration of MainActivity attach and finish onCreate
     */
    fun sumUpMainActivityStartDuration(): Duration {
        return mainActivityAttachedBaseContext +
            mainActivityClassAttrInit +
            mainActivityOnCreate +
            mainActivityOnStart +
            mainActivityOnResume
    }

    fun isWarmStart(): Boolean {
        return (initActivityAttachedBaseContext + initActivityClassAttrInit + initActivityOnCreate).isPositive()
    }

    fun isColdStart(): Boolean {
        return (dexLoad + applicationOnCreate + applicationClassAttrInit).isPositive()
    }

    fun isOnBoardingFlow(): Boolean {
        return onBoardingActivityDuration.isPositive()
    }

    fun reset() {
        dexLoad = Duration.ZERO
        applicationClassAttrInit = Duration.ZERO
        applicationOnCreate = Duration.ZERO
        mainActivityCreateStart = null
        mainActivityAttachedBaseContext = Duration.ZERO
        mainActivityClassAttrInit = Duration.ZERO
        mainActivityOnCreate = Duration.ZERO
        initActivityCreateStart = null
        initActivityRunning = null
        initActivityRunningDuration = Duration.ZERO
        initActivityAttachedBaseContext = Duration.ZERO
        initActivityClassAttrInit = Duration.ZERO
        initActivityOnCreate = Duration.ZERO
        onBoardingActivityDuration = Duration.ZERO
    }
}
