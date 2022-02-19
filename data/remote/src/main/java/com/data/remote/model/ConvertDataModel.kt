package com.data.remote.model


import com.google.gson.annotations.SerializedName

data class ConvertDataModel(
    @SerializedName("fr")
    var fr: String? = "", // EGP
    @SerializedName("id")
    var id: String? = "", // EGP_USD
    @SerializedName("to")
    var to: String? = "", // USD
    @SerializedName("val")
    var valX: Double? = 0.0 // 0.063653
)