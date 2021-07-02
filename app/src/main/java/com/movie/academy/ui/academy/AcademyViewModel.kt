package com.movie.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.movie.academy.data.CourseEntity
import com.movie.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}