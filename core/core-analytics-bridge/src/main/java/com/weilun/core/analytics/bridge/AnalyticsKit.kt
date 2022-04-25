package com.weilun.core.analytics.bridge

interface AnalyticsKit {
    fun trackEvent(name: String, properties: Map<String, Any>)
}
