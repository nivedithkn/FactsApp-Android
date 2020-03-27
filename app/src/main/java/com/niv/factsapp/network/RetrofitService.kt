package com.niv.factsapp.network

import com.niv.factsapp.models.ListingResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * RetrofitService - Service class for api calling
 *
 * @author Nivedith
 * @since 2020-03-26.
 */
interface RetrofitService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFactsListing(): Call<ListingResponse>
}