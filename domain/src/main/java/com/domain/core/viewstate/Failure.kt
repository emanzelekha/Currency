package com.domain.core.viewstate

import com.domain.core.RemoteException
import kotlinx.coroutines.TimeoutCancellationException
import java.io.IOException

class Failure : BaseVS {

    var error: Throwable? = null
    var message: String? = ""
    var code: Int = 0

    constructor(error: Throwable) : super() {
        this.error = error
        this.message = when (error) {
            is RemoteException -> error.errorMessage
            is java.net.ConnectException, is IOException,
            is TimeoutCancellationException,
            -> "من فضلك تأكد من إتصالك بالإنترنت"
            else -> ""
        }
        this.code = when (error) {
            is RemoteException -> error.code
            else -> 0
        }
    }

    constructor(error: Throwable, type: Int) : super() {
        this.error = error
        this.type = type
        this.message = when (error) {
            is RemoteException -> error.errorMessage
            is java.net.ConnectException, is IOException,
            is TimeoutCancellationException,
            -> "من فضلك تأكد من إتصالك بالإنترنت"
            else -> ""
        }
        this.code = when (error) {
            is RemoteException -> error.code
            else -> 0
        }
    }

    constructor(message: String?, code: Int) : super() {
        this.message = message
        this.code = code
    }

    constructor(error: Throwable?, message: String?, code: Int) : super() {
        this.error = error
        this.message = message
        this.code = code
    }

    constructor(message: String?, code: Int, type: Int) : super() {
        this.message = message
        this.code = code
        this.type = type
    }

    constructor(error: Throwable?, message: String?, code: Int, type: Int) : super() {
        this.error = error
        this.message = message
        this.code = code
        this.type = type
    }

    companion object {
        fun get(error: Throwable) = Failure(error)

        fun get(message: String? = "", code: Int = 0) =
            Failure(message, code)

        fun get(error: Throwable? = null, message: String? = "", code: Int = 0) =
            Failure(error, message, code)

        fun getWithType(error: Throwable, type: Int) = Failure(error, type)


        fun getWithType(message: String? = "", code: Int = 0, type: Int) =
            Failure(message, code, type)

        fun getWithType(error: Throwable? = null, message: String? = "", code: Int = 0, type: Int) =
            Failure(error, message, code, type)
    }
}