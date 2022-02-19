package com.ui.core.presentation.fragment

import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class DefaultBaseBottomSheetFragment : BottomSheetDialogFragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super()
}