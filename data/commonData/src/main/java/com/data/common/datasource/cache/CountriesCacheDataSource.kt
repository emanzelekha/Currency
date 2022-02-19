package com.data.common.datasource.cache

import com.domain.common.model.CountriesList
import com.domain.core.Constants.PAGINATION_PAGE_SIZE

interface CountriesCacheDataSource {



    suspend fun insert(countriesEntity: CountriesList): Long


    suspend fun getAllCountries(
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE,
    ): MutableList<CountriesList>

    suspend fun searchCountries(
        query: String,
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE,
    ): MutableList<CountriesList>


//    suspend fun saveFilterOptions(filter: String, order: String)
//
//    suspend fun getFilter(): String?
//
//    suspend fun getOrder(): String?
}
