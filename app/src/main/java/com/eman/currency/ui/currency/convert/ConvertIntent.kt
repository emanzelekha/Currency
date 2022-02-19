package com.eman.currency.ui.currency.convert

import com.domain.common.model.ConvertCurrencyRequest
import com.ui.core.presentation.base.BaseIntent

sealed class ConvertIntent : BaseIntent {
    object HistoryConvert : ConvertIntent()
    object CountriesList : ConvertIntent()
    class ConvertCurrency(var convertCurrencyRequest: ConvertCurrencyRequest) : ConvertIntent()
}