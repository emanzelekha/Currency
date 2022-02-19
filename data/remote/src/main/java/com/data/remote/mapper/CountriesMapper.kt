package com.data.remote.mapper

import com.data.remote.model.CountriesListRemote
import com.domain.common.model.CountriesList
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class CountriesMapper @Inject constructor() :
    Mapper<CountriesListRemote, List<CountriesList>> {
    override fun mapFromRemote(type: CountriesListRemote): List<CountriesList> {
        val listCountries: MutableList<CountriesList> = mutableListOf()
         type.results.map {
                listCountries.add(CountriesList(
                    it.value.currencyId,
                    it.value.currencyName,
                    it.value.currencySymbol,
                    it.value.id
                ))

        }
        return listCountries
    }


}
