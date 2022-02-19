package com.data.remote.service

import com.data.remote.model.CountriesListRemote
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<CurrencyApiService>()
    }

    @GET("countries")
    suspend fun countriesList(
    ): Response<CountriesListRemote>


    @GET("convert")
    suspend fun convertCurrency(
        @Query("q") query: String,
        @Query("compact") compact: String? = "ultra"
    ): Response<Map<String, Double>>

    @GET("convert")
    suspend fun convertCurrencyHistory(
        @Query("date") startDate: String,
        @Query("endDate") endDate: String,
        @Query("compact") compact: String? = "ultra",
        @Query("q") query: String? = "EGP_USD,USD_EGP"

    ): Response<Map<String, Map<String, Double>>>
}