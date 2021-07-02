package com.movie.academy.ui.bookmark

import com.movie.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShare(course: CourseEntity)
}
