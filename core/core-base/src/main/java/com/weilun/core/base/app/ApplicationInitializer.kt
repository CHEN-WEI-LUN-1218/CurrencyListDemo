package com.weilun.core.base.app


/**
 * This Initializer is only work for init AppLogic, Api, Services.
 * Should not set up any user specify field here since this is tight to App.onCreate()
 * */
class ApplicationInitializer(
        private val appLogicRunner: Lazy<AppLogicRunner>
) {

    fun initApp() {
        executeAppLogic()
    }

    internal fun executeAppLogic() {
        appLogicRunner.value.execute()
    }
}