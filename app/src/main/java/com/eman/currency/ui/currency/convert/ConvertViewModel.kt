package com.eman.currency.ui.currency.convert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.domain.common.model.ConvertCurrencyHistoryRequest
import com.domain.common.model.CountriesList
import com.domain.common.usecase.currency.CountriesUseCase
import com.domain.common.usecase.currency.CurrencyConvertUseCase
import com.domain.common.usecase.currency.CurrencyHistoryUseCase
import com.domain.common.viewstate.currency.ConvertStateResult
import com.domain.common.viewstate.currency.CountriesStateResult
import com.domain.core.viewstate.Loading
import com.ui.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ConvertViewModel
@Inject internal constructor(
    private val currencyHistoryUseCase: CurrencyHistoryUseCase,
    private val currencyConvertUseCase: CurrencyConvertUseCase,
    private val countriesUseCase: CountriesUseCase
) : BaseViewModel<ConvertIntent>() {
    var countriesPage = 1
    val _from = MutableLiveData<CountriesList>()
    val from: LiveData<CountriesList>
        get() = _from

    val _to = MutableLiveData<CountriesList>()
    val to: LiveData<CountriesList>
        get() = _to


    var price = 1.0


    override fun processIntents(intent: ConvertIntent) {
        viewModelScope.launch {

            when (intent) {
                is ConvertIntent.ConvertCurrency -> {
                    convertCurrency(intent)
                }
                is ConvertIntent.HistoryConvert -> {
                    getHistory()
                }
                is ConvertIntent.CountriesList -> {
                    getCountries()
                }
            }

        }
    }


    private fun getHistory() {
        viewModelScope.launch {
            currencyHistoryUseCase(ConvertCurrencyHistoryRequest("", ""))
                .map { it.apply { type = ConvertStateResult.TYPE } }
                .onStart {
                    emit(Loading(true))
                }
                .onCompletion {
                    emit(Loading(false))
                }.collect(::sendState)
        }
    }

    private fun getCountries() {
        viewModelScope.launch {
            countriesUseCase(countriesPage)
                .map { it.apply { type = CountriesStateResult.TYPE } }
                .onStart {
                    emit(Loading(true))
                }
                .onCompletion {
                    emit(Loading(false))
                }.collect(::sendState)
        }
    }

    private fun convertCurrency(intent: ConvertIntent.ConvertCurrency) {
        viewModelScope.launch {
            currencyConvertUseCase(intent.convertCurrencyRequest)
                .map { it.apply { type = ConvertStateResult.TYPE } }
                .onStart {
                    emit(Loading(true))
                }
                .onCompletion {
                    emit(Loading(false))
                }.collect(::sendState)
        }
    }

    fun calculateValue(price: Double, amount: Double): Double {
        var result = 0.0

        if (amount > 0.0)
            result = if (price <= 1.0) {
                price * amount
            } else {
                amount.div(price)
            }

        return result
    }

}