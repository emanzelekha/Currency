package com.ui.core.presentation.base

import com.domain.core.viewstate.BaseVS
import kotlinx.coroutines.channels.Channel

interface BaseView<I : BaseIntent, in S : BaseVS> {

    fun intents(): Channel<I>

    fun render(state: S)
}