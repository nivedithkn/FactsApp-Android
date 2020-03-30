package com.niv.factsapp.models

import androidx.databinding.BaseObservable

/**
 * FactsListItem - Data class for fact item
 *
 * @author Nivedith
 * @since 2020-03-27.
 */
data class FactsListItem(
    val title: String?,
    val description: String?,
    val imageHref: String?
) : BaseObservable()