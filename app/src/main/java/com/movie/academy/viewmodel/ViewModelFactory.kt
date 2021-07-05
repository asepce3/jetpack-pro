package com.movie.academy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.academy.data.source.AcademyRepository
import com.movie.academy.di.Injection
import com.movie.academy.ui.academy.AcademyViewModel
import com.movie.academy.ui.bookmark.BookmarkViewModel
import com.movie.academy.ui.detail.DetailCourseViewModel
import com.movie.academy.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val academyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) ->
                AcademyViewModel(academyRepository) as T
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) ->
                DetailCourseViewModel(academyRepository) as T
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) ->
                BookmarkViewModel(academyRepository) as T
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) ->
                CourseReaderViewModel(academyRepository) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}