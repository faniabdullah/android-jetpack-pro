package com.bangkit.faniabdullah_jetpack.di

import android.content.Context
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.LocalDataSource
import com.bangkit.faniabdullah_jetpack.data.source.local.room.MovieDatabase
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.utils.AppExecutors

object Injection {

    fun provideCatalogRepository(context: Context): CatalogMovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return CatalogMovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}