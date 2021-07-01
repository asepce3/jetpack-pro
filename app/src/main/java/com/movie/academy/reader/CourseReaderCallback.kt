package com.movie.academy.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}