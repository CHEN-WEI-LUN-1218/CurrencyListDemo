package com.weilun.currency.list.core.implementation

import app.cash.turbine.test
import com.weilun.currency.list.core.bridge.CurrencyDataRepository
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.core.implementation.internal.manager.CurrenciesDataManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalTime
class AppCurrencyDataRepositoryTest {

    @Mock
    lateinit var currenciesDataManager: CurrenciesDataManager

    private lateinit var repo: CurrencyDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repo = AppCurrencyDataRepository(lazy { currenciesDataManager })
    }

    @Test
    fun init() = runBlockingTest {
        repo.init()
        Mockito.verify(currenciesDataManager).updateCurrencies()
    }

    @Test
    fun sortCurrencies_asc() = runBlockingTest {
        repo.sortCurrencies(CurrenciesSorting.ASC)
        Mockito.verify(currenciesDataManager).sortCurrencies(CurrenciesSorting.ASC)
    }

    @Test
    fun sortCurrencies_desc() = runBlockingTest {
        repo.sortCurrencies(CurrenciesSorting.DESC)
        Mockito.verify(currenciesDataManager).sortCurrencies(CurrenciesSorting.DESC)
    }

    @Test
    fun getCurrencies() = runBlockingTest {
        whenever(currenciesDataManager.whenCurrenciesDataStateChanged()).thenReturn(
            flowOf(
                CurrencyState.DEFAULT.copy(
                    currencies = listOf(
                        CurrencyData("id1", "name1", "symbol1"),
                        CurrencyData("id2", "name2", "symbol2")
                    ),
                    sortingState = CurrenciesSorting.ASC
                )
            )
        )
        repo.getCurrencies().test {
            val item = expectItem()
            Assert.assertEquals(item.currencies.size, 2)
            Assert.assertEquals(item.sortingState, CurrenciesSorting.ASC)
            Assert.assertEquals("id1", item.currencies[0].id)
            Assert.assertEquals("id2", item.currencies[1].id)
            Assert.assertEquals("name1", item.currencies[0].name)
            Assert.assertEquals("name2", item.currencies[1].name)
            Assert.assertEquals("symbol1", item.currencies[0].symbol)
            Assert.assertEquals("symbol2", item.currencies[1].symbol)
            cancelAndIgnoreRemainingEvents()
        }
    }
}