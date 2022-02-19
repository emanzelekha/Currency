package com.domain.common.viewstate.currency

import com.domain.common.model.ConvertCurrencyResult
import com.domain.core.viewstate.BaseVS

class ConvertStateResult (val data: ConvertCurrencyResult,
) : BaseVS() {

    companion object {
        const val TYPE = 10
    }
}
