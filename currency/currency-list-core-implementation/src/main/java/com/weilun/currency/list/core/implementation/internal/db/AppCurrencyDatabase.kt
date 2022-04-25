package com.weilun.currency.list.core.implementation.internal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weilun.currency.list.core.implementation.internal.db.dao.CurrenciesDao
import com.weilun.currency.list.core.implementation.internal.db.entities.Currency

@Database(
    entities = [
        Currency::class
    ],
    exportSchema = true,
    version = 1)
abstract class AppCurrencyDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "com.weilun.currency.data"
    }

    abstract fun currenciesDao(): CurrenciesDao
}