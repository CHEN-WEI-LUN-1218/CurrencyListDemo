package com.weilun.currency.list.core.implementation.internal.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.weilun.currency.list.core.implementation.internal.db.entities.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCurrency(currency: Currency)

    @Insert(onConflict = REPLACE)
    suspend fun insertCurrencies(currencies: List<Currency>)

    @Query("SELECT * FROM currencies")
    fun getCurrencies(): Flow<List<Currency>>
}