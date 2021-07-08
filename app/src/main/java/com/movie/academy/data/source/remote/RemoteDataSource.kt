package com.movie.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import com.movie.academy.data.source.remote.response.ContentResponse
import com.movie.academy.data.source.remote.response.CourseResponse
import com.movie.academy.data.source.remote.response.ModuleResponse
import com.movie.academy.utils.EspressoIdlingResource
import com.movie.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        EspressoIdlingResource.increment()
        handler.post {
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }
    }


    fun getModules(courseId: String, callback: LoadModuleCallback) {
        EspressoIdlingResource.increment()
        handler.post {
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        }
    }


    fun getContent(moduleId: String, callback: LoadContentCallback) {
        EspressoIdlingResource.increment()
        handler.post {
            callback.onContentReceived(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        }
    }


    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)
    }

    interface LoadModuleCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponses: ContentResponse)
    }
}