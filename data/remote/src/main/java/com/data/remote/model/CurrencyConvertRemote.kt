package com.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyConvertRemote(
    @SerializedName("results")
    @Expose var result: List<Map<String,ConvertDataModel>>,
)
