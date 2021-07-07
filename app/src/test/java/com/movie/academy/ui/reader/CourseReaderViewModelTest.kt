package com.movie.academy.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.movie.academy.data.ContentEntity
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
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private val course = DataDummy.generateDummyCourses()[0]
    private val courseId = course.courseId
    private val modules = DataDummy.generateDummyModules(courseId)
    private val moduleId = modules[0].moduleId

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observerModule: Observer<ModuleEntity>

    @Mock
    private lateinit var observerModules: Observer<List<ModuleEntity>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
        viewModel.setSelectedModule(moduleId)

        modules[0].contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun getModules() {
        val liveDataModules = MutableLiveData<List<ModuleEntity>>()
        liveDataModules.value = modules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(liveDataModules)
        val modules = viewModel.getModules().value
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(modules)
        assertEquals(this.modules.size, modules?.size)

        viewModel.getModules().observeForever(observerModules)
        verify(observerModules).onChanged(modules)
    }

    @Test
    fun getSelectedModule() {
        val liveDataModule = MutableLiveData<ModuleEntity>()
        liveDataModule.value = modules[0]
        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(liveDataModule)
        val module = viewModel.getSelectedModule().value
        verify(academyRepository).getContent(courseId, moduleId)
        assertNotNull(module)
        assertEquals(modules[0], module)

        assertNotNull(module?.contentEntity?.content)
        assertEquals(modules[0].contentEntity?.content, module?.contentEntity?.content)

        viewModel.getSelectedModule().observeForever(observerModule)
        verify(observerModule).onChanged(modules[0])
    }
}