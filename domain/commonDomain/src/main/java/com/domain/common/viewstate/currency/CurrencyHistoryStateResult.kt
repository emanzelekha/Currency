package com.domain.common.viewstate.currency

import com.domain.common.model.ConvertCurrencyResult
import com.domain.core.viewstate.BaseVS

class CurrencyHistoryStateResult (val data: List<ConvertCurrencyResult>,
) : BaseVS() {

    companion object {
        const val TYPE = 12
    }
}
