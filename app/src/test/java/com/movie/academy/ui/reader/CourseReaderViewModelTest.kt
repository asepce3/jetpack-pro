package com.movie.academy.ui.reader

import com.movie.academy.data.ContentEntity
import com.movie.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private val course = DataDummy.generateDummyCourses()[0]
    private val courseId = course.courseId
    private val modules = DataDummy.generateDummyModules(courseId)
    private val moduleId = modules[0].moduleId

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel()
        viewModel.setSelectedCourse(courseId)
        viewModel.setSelectedModule(moduleId)

        modules[0].contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun getModules() {
        val modules = viewModel.getModules()
        assertNotNull(modules)
        assertEquals(this.modules.size, modules.size)
    }

    @Test
    fun getSelectedModule() {
        val module = viewModel.getSelectedModule()
        assertNotNull(module)
        assertEquals(modules[0], module)

        assertNotNull(module.contentEntity?.content)
        assertEquals(modules[0].contentEntity?.content, module.contentEntity?.content)
    }
}