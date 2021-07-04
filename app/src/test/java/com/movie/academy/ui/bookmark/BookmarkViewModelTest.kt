package com.movie.academy.ui.bookmark

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmarks() {
        val bookmarks = viewModel.getBookmarks()
        assertNotNull(bookmarks)
        assertEquals(5, bookmarks.size)
    }
}