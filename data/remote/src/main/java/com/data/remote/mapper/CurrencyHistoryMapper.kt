package com.data.remote.mapper

import com.domain.common.model.ConvertCurrencyResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CurrencyHistoryMapper @Inject constructor() :
    Mapper<Map<String, Map<String, Double>>, List<ConvertCurrencyResult>> {
    override fun mapFromRemote(type: Map<String, Map<String, Double>>): List<ConvertCurrencyResult> {
        val listCurrency: MutableList<ConvertCurrencyResult> = mutableListOf()
        type.entries.map {
            val convertType = it.key
            it.value.map {
                val map = it
                val listCurrencyItem = ConvertCurrencyResult()
                listCurrencyItem.type = convertType
                listCurrencyItem.day = map.key
                listCurrencyItem.price = map.value
                listCurrency.add(listCurrencyItem)

            }


        }
        return listCurrency
    }


}