package com.domain.common.usecase.currency

import com.domain.common.model.ConvertCurrencyHistoryRequest
import com.domain.common.repository.CurrencyRepository
import com.domain.core.dispatchers.CoroutineDispatchers
import com.domain.core.viewstate.BaseVS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CurrencyHistoryUseCase @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val repository: CurrencyRepository,
) {

    operator fun invoke(
        convertCurrencyHistoryRequest: ConvertCurrencyHistoryRequest,
    ): Flow<BaseVS> = repository.convertCurrencyHistory(
        convertCurrencyHistoryRequest
    ).flowOn(dispatchers.io)
}
