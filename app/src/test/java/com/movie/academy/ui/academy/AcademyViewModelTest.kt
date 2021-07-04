package com.movie.academy.ui.academy

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courses = viewModel.getCourses()
        assertNotNull(courses)
        assertEquals(5, courses.size)
    }
}