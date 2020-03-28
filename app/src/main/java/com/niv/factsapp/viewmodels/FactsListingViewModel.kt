package com.niv.factsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niv.factsapp.models.FactsListItem
import com.niv.factsapp.models.ListingResponse
import com.niv.factsapp.repository.RepoFactsListing
import com.niv.factsapp.repository.Resource
import kotlinx.coroutines.*

/**
 * FactsListingViewModel - A viewmodel class
 *
 * @author Nivedith
 * @since 2020-03-27.
 */
class FactsListingViewModel : ViewModel() {

    private val repoMainListing = RepoFactsListing

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private var items: MutableList<FactsListItem>? = null

    private var response: ListingResponse? = null

    var title: String? = null

    val hasData get() = items?.size!! > 0


    /**
     * Method to load the page
     */
    fun loadPage(): LiveData<Resource<ListingResponse>> {
        repoMainListing.clearAll()
        return nextPage()
    }

    /**
     * Method load the page
     *
     * @return livedata object of response
     */
    fun nextPage(): LiveData<Resource<ListingResponse>> {

        val liveData = MutableLiveData<Resource<ListingResponse>>()

        liveData.value = Resource.loading()


        uiScope.launch {

            val d = async(Dispatchers.IO) {
                repoMainListing.getList()
            }

            val result = d.await()

            if (result.isSuccess()) {

                response = result.contents
                items = result.contents?.rows?.toMutableList()
                title = result?.contents?.title

                if (response?.error != null) {
                    liveData.value = Resource.empty()
                } else {
                    liveData.value = Resource.success(response)
                }

            } else {

                liveData.value = Resource.error(result.error?.localizedMessage ?: "Unknown error")

            }

        }

        return liveData
    }

}