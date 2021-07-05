package com.movie.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.movie.academy.data.CourseEntity
import com.movie.academy.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}