package com.movie.academy.bookmark

import com.movie.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShare(course: CourseEntity)
}
