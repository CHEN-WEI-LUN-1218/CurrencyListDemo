package com.weilun.currency.list.core.implementation.internal.network

import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import retrofit2.http.GET

//Assuming Currencies Data come from Network
interface CurrenciesNetworkApi {

    //Directly return Currency which we use for Db. In case we don't have to implemeant networking later for now
    @GET("someendpoint/currencies")
    fun provideCurrencies(): List<Currency>
}