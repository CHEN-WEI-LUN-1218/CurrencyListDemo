package com.weilun.currency.list.core.implementation.internal.factory

import com.weilun.currency.list.core.bridge.model.CurrenciesSorting
import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CurrenciesStateFactoryTest {

    private lateinit var factory: CurrenciesStateFactory

    @Before
    fun setUp() {
        factory = CurrenciesStateFactory()
    }

    @Test
    fun fromCurrencyEntity() {
        val currencyA = Currency("a", "a", "a")
        val currencyB = Currency("b", "b", "b")
        val currencyC = Currency("c", "c", "c")

        val actual = factory.fromCurrencyEntity(listOf(currencyA, currencyB, currencyC), CurrenciesSorting.ASC)
        Assert.assertEquals("a", actual.currencies[0].id)
        Assert.assertEquals("b", actual.currencies[1].id)
        Assert.assertEquals("c", actual.currencies[2].id)

        val actual2 = factory.fromCurrencyEntity(listOf(currencyA, currencyB, currencyC), CurrenciesSorting.DESC)
        Assert.assertEquals("c", actual2.currencies[0].id)
        Assert.assertEquals("b", actual2.currencies[1].id)
        Assert.assertEquals("a", actual2.currencies[2].id)
    }
}