package com.weilun.core.analytics.implementation

import com.weilun.core.analytics.bridge.AnalyticsKit
import com.weilun.core.utils.log.logDebug

class AppAnalyticsKit : AnalyticsKit {
    override fun trackEvent(name: String, properties: Map<String, Any>) {
        logDebug("Event tracked : [$name], properties : $properties")
    }
}
