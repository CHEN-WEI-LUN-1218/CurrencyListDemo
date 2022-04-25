package com.weilun.currency.list.core.implementation.applogic

import com.weilun.currency.list.core.bridge.CurrencyDataRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AppCurrencyDataAppLogicTest {
    @Mock
    lateinit var repository: CurrencyDataRepository

    private lateinit var appLogic: AppCurrencyDataAppLogic

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        appLogic = AppCurrencyDataAppLogic(lazy { repository })
    }

    @Test
    fun execute() = runBlockingTest {
        appLogic.execute()
        Mockito.verify(repository).init()
    }
}