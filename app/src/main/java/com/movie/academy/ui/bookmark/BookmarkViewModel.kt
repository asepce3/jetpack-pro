package com.movie.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.movie.academy.data.CourseEntity
import com.movie.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}