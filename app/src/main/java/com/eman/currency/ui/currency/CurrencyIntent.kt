package com.eman.currency.ui.currency

import com.ui.core.presentation.base.BaseIntent

sealed class CurrencyIntent : BaseIntent {

    object LoadFirst : CurrencyIntent()
    class CountriesSearch(var searchQuery: String? = null) : CurrencyIntent()
    class CountriesMorePage(var page: Int) : CurrencyIntent()
}