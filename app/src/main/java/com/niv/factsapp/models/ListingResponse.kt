package com.niv.factsapp.models

/**
 * ListingResponse - A class representation for listing response
 *
 * @author Nivedith
 * @since 2020-03-27.
 */
data class ListingResponse(val data: ListingResponseData) : SimpleResponse() {
    data class ListingResponseData(val title: Long, val rows: Array<FactsListItem>)
}