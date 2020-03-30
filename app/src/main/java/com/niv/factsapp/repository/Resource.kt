package com.niv.factsapp.repository

/**
 * Resource - a resource as a result of an action.
 *
 * @author Nivedith
 * @since 2020-03-27.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    fun isSuccess() = status == Status.SUCCESS

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.PROGRESSING, null, null)
        }

        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY_RESPONSE, null, null)
        }
    }
}

enum class Status {
    EMPTY_RESPONSE,
    PROGRESSING,
    LOADING_MORE,
    SWIPE_RELOADING,
    SUCCESS,
    ERROR
}