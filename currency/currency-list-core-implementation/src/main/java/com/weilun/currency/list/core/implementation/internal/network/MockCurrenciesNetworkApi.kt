package com.weilun.currency.list.core.implementation.internal.network

import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import retrofit2.http.GET

//Assuming Currencies Data come from Network
class MockCurrenciesNetworkApi : CurrenciesNetworkApi {
  override fun provideCurrencies(): List<Currency> {
    return listOf(
        Currency("BTC", "Bitcoin", "BTC")
    )
  }
}