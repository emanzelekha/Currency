package com.data.common.datasource.remote

import com.domain.common.model.ConvertCurrencyHistoryRequest
import com.domain.common.model.ConvertCurrencyResult
import com.domain.common.model.CountriesList

interface CurrencyRemoteDataSource {

    suspend fun countriesList(
    ): List<CountriesList>

    suspend fun convertCurrency(
        query: String,
    ): ConvertCurrencyResult

    suspend fun convertCurrencyHistory(
        request: ConvertCurrencyHistoryRequest
    ): List<ConvertCurrencyResult>
}
