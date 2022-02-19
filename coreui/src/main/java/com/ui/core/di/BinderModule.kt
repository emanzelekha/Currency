package com.ui.core.di

import com.data.cache.source.CountriesCacheDataSourceImpl
import com.data.common.datasource.cache.CountriesCacheDataSource

import com.data.common.datasource.remote.CurrencyRemoteDataSource

import com.data.common.repository.CurrencyRepositoryImpl
import com.data.remote.source.CurrencyRemoteDataSourceImpl
import com.domain.common.repository.CurrencyRepository
import com.domain.core.dispatchers.CoroutineDispatchers
import com.domain.core.dispatchers.CoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindCoroutineDispatchers(coroutineDispatchersImpl: CoroutineDispatchersImpl): CoroutineDispatchers



    @Binds
    abstract fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    abstract fun bindCurrencyRemoteDataSource(currencyRemoteDataSourceImpl: CurrencyRemoteDataSourceImpl): CurrencyRemoteDataSource

    @Binds
    abstract fun bindCountriesCacheDataSource(countriesCacheDataSourceImpl: CountriesCacheDataSourceImpl): CountriesCacheDataSource
}
