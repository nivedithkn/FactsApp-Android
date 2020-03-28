package com.niv.factsapp.repository

import androidx.lifecycle.MutableLiveData
import com.niv.factsapp.models.FactsListItem
import com.niv.factsapp.models.Failure
import com.niv.factsapp.models.ListingResponse
import com.niv.factsapp.models.Success
import com.niv.factsapp.network.FactsApi
import kotlinx.coroutines.Job

/**
 * FactsListRepo
 *
 * @author Nivedith
 * @since 2020-03-27.
 */

object RepoFactsListing {

    private var job = Job()

    private val items: ArrayList<FactsListItem> = ArrayList()
    private var response: ListingResponse? = null

    /**
     * Method to get the list
     */
    fun getList(): RepoResult<ListingResponse> {

        val liveData = MutableLiveData<Resource<ListingResponse>>()
        liveData.postValue(Resource.loading())

        if(job.isCompleted || job.isCancelled){
            job = Job()
        }



        // Fetch the data.
        val result = FactsApi.getFactsListing()

        return when (result) {

            is Success -> {

                result.contents.let {

                    //items.addAll(it)
                    items.addAll(it.rows)
                    response = result.contents
                    RepoResult(contents = it)
                }

            }

            is Failure -> {
                RepoResult(result.errorCode, result.throwable)
            }

        }

    }

    fun getAll(): List<FactsListItem> = items

    /**
     * Method to clear all items in the list
     */
    fun clearAll() {
        items.clear()
    }
}