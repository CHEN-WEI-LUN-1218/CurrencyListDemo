package com.weilun.currency.list.ui.implementation.viewmodel

import app.cash.turbine.test
import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.currency.list.core.bridge.CurrencyDataRepository
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalTime class CurrencyListViewModelTest {


    @Mock
    lateinit var repository: CurrencyDataRepository

    private val dispatchersProvider: DispatchersProvider = DispatchersProvider(_ioDispatcher = TestCoroutineDispatcher())

    private lateinit var viewModel: CurrencyListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CurrencyListViewModel(lazy { repository }, lazy { dispatchersProvider })
    }

    @Test
    fun onResume() = runBlockingTest {
        val currenciesState = CurrencyState.DEFAULT.copy(
            currencies = listOf(
                CurrencyData("id1", "name1", "symbol1"),
                CurrencyData("id2", "name2", "symbol2")
            ),
            sortingState = CurrenciesSorting.ASC
        )
        whenever(repository.getCurrencies()).thenReturn(flowOf(currenciesState))
        viewModel.onResume()

        viewModel.whenCurrencyDataChanged().test {
            val item = expectItem()
            Assert.assertEquals(currenciesState, item.currencyState)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun handleViewAction() = runBlockingTest {
        viewModel.handleViewAction(CurrencyListViewAction.Sort(CurrenciesSorting.DESC))
        Mockito.verify(repository).sortCurrencies(CurrenciesSorting.DESC)

        viewModel.handleViewAction(CurrencyListViewAction.SelectItem(CurrencyData("id1", "name1", "symbol1")))
    }
}