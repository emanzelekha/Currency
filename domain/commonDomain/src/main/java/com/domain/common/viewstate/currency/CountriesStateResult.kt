package com.domain.common.viewstate.currency

import com.domain.common.model.CountriesList
import com.domain.core.viewstate.BaseVS

class CountriesStateResult (val data: List<CountriesList>,
) : BaseVS() {

    companion object {
        const val TYPE = 11
    }
}
