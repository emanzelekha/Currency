package com.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountriesListRemote(
    @SerializedName("results")
    @Expose
    var results: Map<String,CountriesModel>,

)
