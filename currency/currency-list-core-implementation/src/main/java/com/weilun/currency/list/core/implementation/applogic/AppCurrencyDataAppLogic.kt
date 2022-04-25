package com.weilun.currency.list.core.implementation.applogic

import com.weilun.core.applogic.AppLogic
import com.weilun.currency.list.core.bridge.CurrencyDataRepository

class AppCurrencyDataAppLogic(
    private val repository: Lazy<CurrencyDataRepository>
) : AppLogic {
    override suspend fun execute() {
        repository.value.init()
    }
}