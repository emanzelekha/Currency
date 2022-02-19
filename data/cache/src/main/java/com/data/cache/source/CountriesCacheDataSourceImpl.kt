package com.data.cache.source

import com.data.cache.db.CountriesDao
import com.data.cache.mapper.CountriesMapper
import com.data.common.datasource.cache.CountriesCacheDataSource
import com.domain.common.model.CountriesList
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CountriesCacheDataSourceImpl @Inject constructor(
    private val countriesDao: CountriesDao,
    private val countriesMapper: CountriesMapper

) : CountriesCacheDataSource {
    override suspend fun insert(countriesEntity: CountriesList): Long {
        return countriesDao.insert( countriesMapper.mapToCached(countriesEntity))
    }

    override suspend fun getAllCountries(page: Int, pageSize: Int): MutableList<CountriesList> {
        return countriesDao.getAllCountries(page, pageSize)
            .map { countriesMapper.mapFromCached(it) }.toMutableList()
    }

    override suspend fun searchCountries(
        query: String,
        page: Int,
        pageSize: Int
    ): MutableList<CountriesList> {
        return countriesDao.getAllCountries(page, pageSize)
            .map { countriesMapper.mapFromCached(it) }.toMutableList()    }


}
