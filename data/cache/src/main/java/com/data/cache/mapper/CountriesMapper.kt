package com.data.cache.mapper

import com.data.cache.model.CountriesCached
import com.domain.common.model.CountriesList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesMapper @Inject constructor() : Mapper<CountriesCached, CountriesList> {
    override fun mapFromCached(type: CountriesCached): CountriesList {
        return CountriesList(
            type.currencyId,
            type.currencyName,
            type.currencySymbol,
            type.id,
            type.name,
            "https://flagcdn.com/108x81/${type.id.lowercase()}.png"
        )
    }


    override fun mapToCached(type: CountriesList): CountriesCached {
        return CountriesCached(
            type.id!!,
            type.currencyId,
            type.currencyName,
            type.currencySymbol,
            type.name
        )
    }
}
