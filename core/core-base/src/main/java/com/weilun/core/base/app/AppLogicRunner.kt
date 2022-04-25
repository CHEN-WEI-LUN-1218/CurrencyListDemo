package com.weilun.core.base.app

import com.weilun.core.applogic.AppLogic
import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.core.utils.log.logError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppLogicRunner(
    private val appLogics: Set<AppLogic>,
    private val dispatchersProvider: DispatchersProvider
) {

    private val handler by lazy {
        CoroutineExceptionHandler { _, exception ->
            logError(exception)
        }
    }

    //Use a SupervisorJob to avoid child applogic effect each other
    private val job by lazy { SupervisorJob() }
    private val scope by lazy { CoroutineScope(dispatchersProvider.appInitDispatcher + handler + job) }

    fun execute() {
        appLogics.forEach { applogic ->
            scope.launch {
                applogic.execute()
            }
        }
    }
}