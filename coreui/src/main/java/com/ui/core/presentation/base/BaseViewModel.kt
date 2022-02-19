package com.ui.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.core.viewstate.BaseVS
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : BaseIntent> : ViewModel() {

    val intentsChannel = Channel<I>()

    val states = Channel<BaseVS>()

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentsChannel.consumeAsFlow().collect(::processIntents)
        }
    }

    abstract fun processIntents(intent: I)

    fun sendState(state: BaseVS) {
        viewModelScope.launch {
            states.send(state)
        }
    }
}