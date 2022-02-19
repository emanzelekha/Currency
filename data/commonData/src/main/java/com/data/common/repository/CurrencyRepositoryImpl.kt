package com.data.common.repository

import com.data.common.datasource.cache.CountriesCacheDataSource
import com.data.common.datasource.remote.CurrencyRemoteDataSource
import com.domain.common.model.ConvertCurrencyHistoryRequest
import com.domain.common.model.ConvertCurrencyRequest
import com.domain.common.repository.CurrencyRepository
import com.domain.common.viewstate.currency.ConvertStateResult
import com.domain.common.viewstate.currency.CountriesStateResult
import com.domain.common.viewstate.currency.CurrencyHistoryStateResult
import com.domain.core.Constants
import com.domain.core.viewstate.BaseVS
import com.domain.core.viewstate.Failure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource,
    private val currencyCacheDataSource: CountriesCacheDataSource,
) : CurrencyRepository {
    override fun searchCountries(query: String, page: Int): Flow<BaseVS> = flow {

        try {
            withTimeout(Constants.NETWORK_TIMEOUT) {
                emit(CountriesStateResult(currencyCacheDataSource.searchCountries(query, page)))
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            println("new Error ${throwable.message}")
            emit(CountriesStateResult(currencyCacheDataSource.searchCountries(query, page)))
        }

    }

    override fun getCountries(page: Int): Flow<BaseVS> = flow {
        val savedList = currencyCacheDataSource.getAllCountries(page)
        if (savedList.size > 0) {
            emit(CountriesStateResult(savedList))
        } else {

            try {
                withTimeout(Constants.NETWORK_TIMEOUT) {
                    val response = currencyRemoteDataSource.countriesList()
                    for (countriesList in response) {
                        currencyCacheDataSource.insert(countriesList)
                    }

                    emit(CountriesStateResult(currencyCacheDataSource.getAllCountries(page)))
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                println("new Error ${throwable.message}")

                emit(CountriesStateResult(currencyCacheDataSource.getAllCountries(page)))
            }

        }
    }


    override fun convertCurrency(request: ConvertCurrencyRequest): Flow<BaseVS> = flow {

        try {
            withTimeout(Constants.NETWORK_TIMEOUT) {
                val response = currencyRemoteDataSource.convertCurrency(request.query)
                emit(ConvertStateResult(response))
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            println("new Error ${throwable.message}")
            emit(Failure.get(error = throwable))
        }

    }

    override fun convertCurrencyHistory(request: ConvertCurrencyHistoryRequest): Flow<BaseVS> =
        flow {
            try {
                withTimeout(Constants.NETWORK_TIMEOUT) {
                    val response = currencyRemoteDataSource.convertCurrencyHistory(request)
                    emit(CurrencyHistoryStateResult(response))
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                println("new Error ${throwable.message}")
                emit(Failure.get(error = throwable))
            }

        }


}
