package com.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "countries")
data class CountriesCached(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "currencyId")
    var currencyId: String? = null,

    @ColumnInfo(name = "currencyName")
    var currencyName: String? = null,

    @ColumnInfo(name = "currencySymbol")
    var currencySymbol: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,


    )
