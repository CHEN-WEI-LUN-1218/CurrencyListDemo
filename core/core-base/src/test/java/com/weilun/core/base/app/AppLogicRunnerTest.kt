package com.weilun.core.base.app

import com.weilun.core.applogic.AppLogic
import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.core.utils.log.logError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AppLogicRunnerTest {

    @Mock
    lateinit var appLogic1: AppLogic

    @Mock
    lateinit var appLogic2: AppLogic

    private val dispatchersProvider = DispatchersProvider(_appInitDispatcher = TestCoroutineDispatcher())

    private lateinit var appLogicRunner: AppLogicRunner

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        appLogicRunner = AppLogicRunner(setOf(appLogic1, appLogic2), dispatchersProvider)
    }

    @Test
    fun execute() = runBlockingTest {
        appLogicRunner.execute()
        Mockito.verify(appLogic1).execute()
        Mockito.verify(appLogic2).execute()
    }
}