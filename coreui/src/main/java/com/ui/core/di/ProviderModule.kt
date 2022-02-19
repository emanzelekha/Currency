package com.ui.core.di


import com.data.remote.service.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {


    @Provides
    fun provideCurrencyAPIService(retrofit: Retrofit): CurrencyApiService = CurrencyApiService(retrofit)

}