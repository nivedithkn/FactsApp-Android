package com.niv.factsapp.preferences

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * PreferenceHelperTest
 *
 * @author Nivedith
 * @since 2020-03-30.
 */
class PreferenceHelperTest {

    @Before
    fun init() {
        PreferenceHelper.initWith(InstrumentationRegistry.getInstrumentation().context)
    }

    @Test
    fun getFactsListItem() {
        val title = "title"
        PreferenceHelper.factsListItem = title
        var testTitle = PreferenceHelper.factsListItem

        assertEquals(title, testTitle)
    }

    @Test
    fun setFactsListItem() {
    }

    @Test
    fun clearAll() {
        var title = "title"
        var desc = "desc"
        PreferenceHelper.factsListItem = title
        PreferenceHelper.factsListItem = desc

        PreferenceHelper.clearAll()
        var testTitle = PreferenceHelper.factsListItem
        var testDesc = PreferenceHelper.factsListItem
        assertEquals("", testTitle)
        assertEquals("", testDesc)
    }
}