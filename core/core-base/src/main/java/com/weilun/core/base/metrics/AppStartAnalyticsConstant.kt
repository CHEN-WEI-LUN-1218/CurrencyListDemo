package com.weilun.core.base.metrics

object AppStartAnalyticsConstant {
    const val EVENT_APP_COLD_START = "application.cold_start"
    const val EVENT_APP_WARM_START = "application.warm_start"
    const val EVENT_APP_HOT_START = "application.hot_start"
    const val EVENT_APPLICATION_START = "application.start"
    const val EVENT_MAIN_ACTIVITY_START = "mainactivity.start"

    // Event for track duration of User start able to use Application in HomeScreen
    const val PROPERTY_DURATION = "duration"
    const val PROPERTY_ATTR_INIT = "attr_init"
    const val PROPERTY_ATTACHED = "attached"
    const val PROPERTY_CREATE = "create"
    const val PROPERTY_START = "start"
    const val PROPERTY_RESUME = "resume"
}
