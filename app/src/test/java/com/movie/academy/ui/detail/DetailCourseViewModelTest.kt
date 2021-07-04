package com.movie.academy.ui.detail

import com.movie.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val course = DataDummy.generateDummyCourses()[0]
    private val courseId = course.courseId
    private val modules = DataDummy.generateDummyModules(courseId)

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel()
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        val course = viewModel.getCourse()
        assertNotNull(course)
        assertEquals(this.course, course)
    }

    @Test
    fun getModules() {
        val modules = viewModel.getModules()
        assertNotNull(modules)
        assertEquals(this.modules.size, modules.size)
    }
}