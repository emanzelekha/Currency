package com.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.cache.model.CountriesCached
import com.domain.core.Constants.PAGINATION_PAGE_SIZE

@Dao
interface CountriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countriesCached: CountriesCached): Long



    @Query(
        """
        SELECT * FROM countries
        WHERE currencyName LIKE '%' || :query || '%'
        OR name LIKE '%' || :query || '%'
        OR currencySymbol LIKE '%' || :query || '%'
        LIMIT (:page * :pageSize)
        """
    )
    suspend fun searchCountries(
        query: String,
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE,
    ): MutableList<CountriesCached>


    @Query(
        """
        SELECT * FROM countries
        LIMIT (:page * :pageSize)
        """
    )
    suspend fun getAllCountries(
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE,
    ): MutableList<CountriesCached>

}
