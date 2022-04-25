package com.weilun.core.base.app

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * This Initializer is only work for init AppLogic, Api, Services.
 * Should not set up any user specify field here since this is tight to App.onCreate()
 * */
class ApplicationInitializerTest {

    @Mock
    lateinit var appLogicRunner: AppLogicRunner

    private lateinit var applicationInitializer: ApplicationInitializer

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        applicationInitializer = ApplicationInitializer(appLogicRunner = lazy { appLogicRunner })
    }

    @Test
    fun initApp() {
        applicationInitializer.initApp()
        Mockito.verify(appLogicRunner).execute()
    }
}