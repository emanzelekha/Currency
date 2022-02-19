package com.ui.core.presentation.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class DefaultBaseFragment : Fragment  {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
}