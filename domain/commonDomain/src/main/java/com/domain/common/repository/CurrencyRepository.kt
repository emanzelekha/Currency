package com.domain.common.repository

import com.domain.common.model.ConvertCurrencyHistoryRequest
import com.domain.common.model.ConvertCurrencyRequest
import com.domain.core.repository.Repository
import com.domain.core.viewstate.BaseVS
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository : Repository {

    fun searchCountries(
        query: String,
        page: Int,
    ): Flow<BaseVS>

    fun getCountries(
        page: Int,
    ): Flow<BaseVS>

    fun convertCurrency(
        request: ConvertCurrencyRequest
    ): Flow<BaseVS>

    fun convertCurrencyHistory(
        request: ConvertCurrencyHistoryRequest
    ): Flow<BaseVS>


}
