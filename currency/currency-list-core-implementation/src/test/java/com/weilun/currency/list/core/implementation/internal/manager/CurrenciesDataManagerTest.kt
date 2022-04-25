package com.weilun.currency.list.core.implementation.internal.manager

import app.cash.turbine.test
import com.weilun.core.utils.log.logDebug
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.core.implementation.internal.db.dao.CurrenciesDao
import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import com.weilun.currency.list.core.implementation.internal.factory.CurrenciesStateFactory
import com.weilun.currency.list.core.implementation.internal.network.CurrenciesNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
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
class CurrenciesDataManagerTest {

    @Mock
    lateinit var networkApi: CurrenciesNetworkApi

    @Mock
    lateinit var currenciesDao: CurrenciesDao

    private val factory: CurrenciesStateFactory by lazy { CurrenciesStateFactory() }

    private lateinit var manager: CurrenciesDataManager

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        manager = CurrenciesDataManager(lazy { networkApi }, lazy { currenciesDao }, lazy { factory })
    }

    @Test
    fun whenCurrenciesDataStateChanged() = runBlockingTest {
        val mockList = listOf(
            Currency("id1", "name1", "symbol1"),
            Currency("id2", "name2", "symbol2")
        )
        whenever(currenciesDao.getCurrencies()).thenReturn(flowOf(mockList))
        manager.whenCurrenciesDataStateChanged().test {
            val actual = expectItem()
            Assert.assertEquals("id2", actual.currencies[0].id)
            Assert.assertEquals("id1", actual.currencies[1].id)
            Assert.assertEquals(CurrenciesSorting.DESC, actual.sortingState)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun updateCurrencies() = runBlockingTest {
        val mockList = listOf(
            Currency("id1", "name1", "symbol1"),
            Currency("id2", "name2", "symbol2")
        )
        whenever(networkApi.provideCurrencies()).thenReturn(mockList)
        manager.updateCurrencies()
        Mockito.verify(currenciesDao).insertCurrencies(mockList)
    }

    @Test
    fun sortCurrencies() = runBlockingTest {
        val mockList = listOf(
            Currency("id1", "name1", "symbol1"),
            Currency("id2", "name2", "symbol2")
        )
        whenever(currenciesDao.getCurrencies()).thenReturn(flowOf(mockList))

        manager.whenCurrenciesDataStateChanged().test {
            val actual = expectItem()
            Assert.assertEquals("id2", actual.currencies[0].id)
            Assert.assertEquals("id1", actual.currencies[1].id)
            Assert.assertEquals(CurrenciesSorting.DESC, actual.sortingState)

            manager.sortCurrencies(CurrenciesSorting.ASC)
            val actual2 = expectItem()
            Assert.assertEquals("id1", actual2.currencies[0].id)
            Assert.assertEquals("id2", actual2.currencies[1].id)
            Assert.assertEquals(CurrenciesSorting.ASC, actual2.sortingState)
        }
    }
}