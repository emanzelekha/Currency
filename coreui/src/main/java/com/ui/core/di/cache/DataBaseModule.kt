package com.ui.core.di.cache

import android.app.Application
import androidx.room.Room
import com.data.cache.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    )
        .build()



    @Provides
    fun provideCountriesDao(db: AppDatabase) = db.getCountriesDao()
}