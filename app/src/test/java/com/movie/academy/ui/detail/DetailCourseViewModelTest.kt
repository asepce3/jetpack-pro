package com.movie.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.movie.academy.data.CourseEntity
import com.movie.academy.data.ModuleEntity
import com.movie.academy.data.source.AcademyRepository
import com.movie.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val course = DataDummy.generateDummyCourses()[0]
    private val courseId = course.courseId
    private val modules = DataDummy.generateDummyModules(courseId)

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<CourseEntity>

    @Mock
    private lateinit var observerModules: Observer<List<ModuleEntity>>

    @get:Rule
    var instantTastExecuteRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        val mutableCourse = MutableLiveData<CourseEntity>()
        mutableCourse.value = course
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(mutableCourse)
        val course = viewModel.getCourse().value
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(course)
        assertEquals(this.course, course)

        viewModel.getCourse().observeForever(observer)
        verify(observer).onChanged(course)
    }

    @Test
    fun getModules() {
        val mutableModules = MutableLiveData<List<ModuleEntity>>()
        mutableModules.value = modules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(mutableModules)
        val modules = viewModel.getModules().value
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(modules)
        assertEquals(this.modules.size, modules?.size)

        viewModel.getModules().observeForever(observerModules)
        verify(observerModules).onChanged(modules)
    }
}