package com.movie.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.movie.academy.data.CourseEntity
import com.movie.academy.data.ModuleEntity
import com.movie.academy.utils.DataDummy

class DetailCourseViewModel : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity {
        lateinit var course: CourseEntity
        val courseEntities = DataDummy.generateDummyCourses()
        for (courseEntity in courseEntities) {
            if (courseEntity.courseId == courseId)
                course = courseEntity
        }

        return course
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}