package com.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.cache.model.CountriesCached

@Database(entities = [CountriesCached::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_db"
    }


    abstract fun getCountriesDao(): CountriesDao

}
