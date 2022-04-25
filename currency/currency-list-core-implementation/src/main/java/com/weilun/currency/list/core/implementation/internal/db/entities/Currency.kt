package com.weilun.currency.list.core.implementation.internal.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class Currency(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "symbol")
        val symbol: String,
)