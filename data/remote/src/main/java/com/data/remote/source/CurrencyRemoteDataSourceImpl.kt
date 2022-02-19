package com.data.remote.source


import com.data.common.datasource.remote.CurrencyRemoteDataSource
import com.data.remote.mapper.CountriesMapper
import com.data.remote.mapper.CurrencyHistoryMapper
import com.data.remote.service.CurrencyApiService
import com.domain.common.model.*
import com.domain.core.RemoteException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService,
    private val countriesMapper: CountriesMapper,
    private val currencyHistoryMapper: CurrencyHistoryMapper,
) : CurrencyRemoteDataSource {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)


    override suspend fun countriesList(): List<CountriesList> {
        val request = currencyApiService.countriesList()
        val data = request.body()
        return if (request.isSuccessful && data != null)
            countriesMapper.mapFromRemote(data)
        else throw RemoteException(request.code(), request.errorBody()?.toString() ?: "")
    }

    override suspend fun convertCurrency(query: String): ConvertCurrencyResult {
        val request = currencyApiService.convertCurrency(query)
        val data = request.body()
        return if (request.isSuccessful && data != null)
            ConvertCurrencyResult().apply {
                this.day = dateFormat.format(Date())
                this.price = data.getValue(query)
                this.type = query
            }
        else throw RemoteException(request.code(), request.errorBody()?.toString() ?: "")
    }

    override suspend fun convertCurrencyHistory(convertCurrencyRequest: ConvertCurrencyHistoryRequest): List<ConvertCurrencyResult> {
        val request = currencyApiService.convertCurrencyHistory(
            dateFormat.format(Date(Date().time - 604800000L).time),
            dateFormat.format(Date().time)
        )
        val data = request.body()
        return if (request.isSuccessful && data != null)
            currencyHistoryMapper.mapFromRemote(data)
        else throw RemoteException(request.code(), request.errorBody()?.toString() ?: "")
    }

}
