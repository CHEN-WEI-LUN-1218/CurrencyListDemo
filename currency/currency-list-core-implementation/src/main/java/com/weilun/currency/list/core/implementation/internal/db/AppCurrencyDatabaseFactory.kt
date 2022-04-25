package com.weilun.currency.list.core.implementation.internal.db

import android.content.Context
import androidx.room.Room

object AppCurrencyDatabaseFactory {
    fun createDb(context: Context): AppCurrencyDatabase {
        return Room.databaseBuilder(context.applicationContext,
            AppCurrencyDatabase::class.java,
            AppCurrencyDatabase.DB_NAME)
            .enableMultiInstanceInvalidation()
            .build()
    }
}