package com.movie.academy.di

import android.content.Context
import com.movie.academy.data.source.AcademyRepository
import com.movie.academy.data.source.remote.RemoteDataSource
import com.movie.academy.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return AcademyRepository.getInstance(remoteDataSource)
    }
}