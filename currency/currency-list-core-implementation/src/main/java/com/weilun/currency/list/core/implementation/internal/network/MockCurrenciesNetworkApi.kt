package com.weilun.currency.list.core.implementation.internal.network

import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import retrofit2.http.GET

//Assuming Currencies Data come from Network
class MockCurrenciesNetworkApi : CurrenciesNetworkApi {
    override fun provideCurrencies(): List<Currency> {
        return listOf(
            Currency("BTC", "Bitcoin", "BTC"),
            Currency("ETH", "Ethereum", "ETH"),
            Currency("XRP", "XRP", "XRP"),
            Currency("BCH", "Bitcoin Cash", "BCH"),
            Currency("LTC", "Litecoin", "LTC"),
            Currency("EOS", "EOS", "EOS"),
            Currency("BNB", "Binance Coin", "BNB"),
            Currency("LINK", "Chainlink", "LINK"),
            Currency("NEO", "NEO", "NEO"),
            Currency("ETC", "Ethereum Classic", "ETC"),
            Currency("ONT", "Ontology", "ONT"),
            Currency("CRO", "Crypto.com Chain", "CRO"),
            Currency("CUC", "Cucumber", "CUC"),
            Currency("USDC", "USD Coin", "USDC")
        )
    }
}