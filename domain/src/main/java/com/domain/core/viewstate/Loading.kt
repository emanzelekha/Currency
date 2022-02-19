package com.domain.core.viewstate

class Loading() : BaseVS() {

    var isLoading = true

    constructor(isLoading: Boolean) : this() {
        this.isLoading = isLoading
    }

    constructor(isLoading: Boolean, type: Int) : this() {
        this.isLoading = isLoading
        this.type = type
    }

    companion object {
        fun get(isLoading: Boolean) = Loading(isLoading)
        fun getWithType(isLoading: Boolean, type: Int) = Loading(isLoading, type)
    }
}