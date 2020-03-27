package com.niv.factsapp.models

/**
 * SimpleResponse - A class represents the base response from the API
 *
 * @author Nivedith
 * @since 2020-03-26.
 */
open class SimpleResponse {

    var status: Int = 0
    var message: String? = null
    var error: Error? = null

    fun isSuccess() = status == 200
}

data class Error(var mobile_errors: MobileErrorData) {
    data class MobileErrorData(var message: String, var field_name: String)
}

sealed class ApiResult<out T : Any>

data class Success<out T : Any>(val contents: T) : ApiResult<T>()
data class Failure(val errorCode: Int, val throwable: Throwable) : ApiResult<Nothing>()