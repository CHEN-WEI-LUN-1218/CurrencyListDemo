package com.weilun.currency.list.ui.implementation.viewholder

import app.cash.turbine.test
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime

@ExperimentalTime
class CurrencyViewItemInteractorTest {

    private lateinit var interactor: CurrencyViewItemInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = CurrencyViewItemInteractor()
    }

    @Test
    fun intercept() = runBlockingTest {
        interactor.whenItemViewIntercept().test {
            interactor.intercept(CurrencyListViewAction.SelectItem(CurrencyData("id", "name", "symbol")))
            val item = expectItem()
            Assert.assertTrue(item is CurrencyListViewAction.SelectItem)
            Assert.assertEquals("id", (item as CurrencyListViewAction.SelectItem).currencyData.id)
            cancelAndIgnoreRemainingEvents()
        }
    }
}