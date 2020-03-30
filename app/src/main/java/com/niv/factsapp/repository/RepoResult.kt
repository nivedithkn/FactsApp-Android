package com.niv.factsapp.repository

/**
 * RepoResult - repo action results
 *
 * @author Nivedith
 * @since 2020-03-27.
 */
data class RepoResult<out T>(
    val code: Int? = null,
    val error: Throwable? = null,
    val contents: T? = null
) {
    fun isSuccess() = error == null || code == null
}