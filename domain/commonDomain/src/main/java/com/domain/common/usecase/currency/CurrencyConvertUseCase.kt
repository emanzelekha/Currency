package com.domain.common.usecase.currency

import com.domain.common.model.ConvertCurrencyRequest
import com.domain.common.repository.CurrencyRepository
import com.domain.core.dispatchers.CoroutineDispatchers
import com.domain.core.viewstate.BaseVS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CurrencyConvertUseCase @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val repository: CurrencyRepository,
) {

    operator fun invoke(
        convertCurrencyRequest: ConvertCurrencyRequest
    ): Flow<BaseVS> = repository.convertCurrency(convertCurrencyRequest).flowOn(dispatchers.io)
}
