package com.data.remote.model


import com.google.gson.annotations.SerializedName

data class CountriesModel(
    @SerializedName("alpha3")
    var alpha3: String? = "", // AFG
    @SerializedName("currencyId")
    var currencyId: String? = "", // AFN
    @SerializedName("currencyName")
    var currencyName: String? = "", // Afghan afghani
    @SerializedName("currencySymbol")
    var currencySymbol: String? = "", // ؋
    @SerializedName("id")
    var id: String? = "", // AF
    @SerializedName("name")
    var name: String? = "" // Afghanistan
)