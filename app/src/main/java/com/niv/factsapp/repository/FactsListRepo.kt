package com.niv.factsapp.repository

import com.niv.factsapp.models.FactsListItem
import com.niv.factsapp.models.Failure
import com.niv.factsapp.models.Success
import com.niv.factsapp.network.FactsApi

/**
 * FactsListRepo
 *
 * @author Nivedith
 * @since 2020-03-27.
 */

object RepoFactsListing {

    private val items: ArrayList<FactsListItem> = ArrayList()

    /**
     * Method to get the list
     */
    fun getList(): RepoResult<List<FactsListItem>> {

        // Fetch the data.
        val result = FactsApi.getFactsListing()

        return when (result) {

            is Success -> {

                result.contents.data.rows.let {

                    items.addAll(it)
                    RepoResult(contents = it.asList())
                }

            }

            is Failure -> {
                RepoResult(result.errorCode, result.throwable)
            }

        }

    }
}