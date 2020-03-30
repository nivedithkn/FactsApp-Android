package com.niv.factsapp.repository

import com.niv.factsapp.models.ListingResponse
import org.junit.Assert.*
import org.junit.Test

/**
 * RepoFactsListingTest - Unit test class for the repository class
 *
 * @author Nivedith
 * @since 2020-03-30.
 */
class RepoFactsListingTest {

    private val repoMainListing = RepoFactsListing

    private val aboutTitle = "About Canada"
    private val rowTitle = "Beavers"
    private val rowDesc =
        "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony"
    private val rowImage =
        "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"

    @Test
    fun getList() {

        var resp = repoMainListing.getList()
        val contents = resp.contents as ListingResponse
        assertNotNull(contents)
        assertNotNull(contents.rows)
        assertNotNull(contents.title)

        assertEquals(aboutTitle, contents.title)
        assertEquals(rowTitle, contents.rows[0].title)
        assertEquals(rowDesc, contents.rows[0].description)
        assertEquals(rowImage, contents.rows[0].imageHref)
    }

    @Test
    fun getAll() {
        var listItems = repoMainListing.getAll()
        val items = listItems

        assertNotNull(items)
        assertEquals(items, listItems)
    }

    @Test
    fun clearAll() {
        var listItems = repoMainListing.getAll()
        val items = listItems

        repoMainListing.clearAll()
        assertTrue(items.isEmpty())
    }
}