package com.bangkit.faniabdullah_jetpack.di

import com.bangkit.faniabdullah_jetpack.data.CatalogMovieMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): CatalogMovieMovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogMovieMovieRepository.getInstance(remoteDataSource)
    }
}