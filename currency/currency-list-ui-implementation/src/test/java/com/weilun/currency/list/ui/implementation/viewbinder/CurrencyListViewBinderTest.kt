package com.weilun.currency.list.ui.implementation.viewbinder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.cash.turbine.test
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.core.bridge.model.CurrencyState
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.adapter.CurrencyListAdapter
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalTime class CurrencyListViewBinderTest {


    @Mock
    lateinit var context: Context

    @Mock
    lateinit var rootView: View

    @Mock
    lateinit var adapter: CurrencyListAdapter

    @Mock
    lateinit var fabSort: FloatingActionButton

    @Mock
    lateinit var rvCurrencies: RecyclerView

    private lateinit var viewBinder: CurrencyListViewBinder

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewBinder = CurrencyListViewBinder(context, rootView, adapter)
        whenever(rootView.findViewById<RecyclerView>(R.id.rvCurrencies)).thenReturn(rvCurrencies)
        whenever(rootView.findViewById<FloatingActionButton>(R.id.fabSort)).thenReturn(fabSort)
        Assert.assertEquals(rvCurrencies, viewBinder.rvCurrencies)
        Assert.assertEquals(fabSort, viewBinder.fabSort)
    }

    @Test
    fun bindViewState() {
        val currenciesState = CurrencyState.DEFAULT.copy(
            currencies = listOf(
                CurrencyData("id1", "name1", "symbol1"),
                CurrencyData("id2", "name2", "symbol2")
            ),
            sortingState = CurrenciesSorting.ASC
        )
        val mockViewState = CurrencyListViewState.DEFAULT.copy(
            currencyState = currenciesState
        )

        viewBinder.bindViewState(mockViewState)
        Assert.assertEquals(CurrenciesSorting.ASC, viewBinder.currentSorting)
        Mockito.verify(adapter).submitList(mockViewState.currencyState.currencies)
    }

    @Test
    fun whenViewActionEmitted() = runBlockingTest {
        viewBinder.whenViewActionEmitted().test {
            viewBinder.updateSorting()
            viewBinder.updateSorting()
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }
}