package com.domain.common.model


data class CountriesList(
    var currencyId: String? = "", // AFN
    var currencyName: String? = "", // Afghan afghani
    var currencySymbol: String? = "", // ؋
    var id: String? = "", // AF
    var name: String? = "",
    var flagName: String = "https://flagcdn.com/108x81/${id?.lowercase()}.png"
)