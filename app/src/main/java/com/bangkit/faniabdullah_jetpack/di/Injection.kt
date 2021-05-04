package com.bangkit.faniabdullah_jetpack.di

import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): CatalogMovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogMovieRepository.getInstance(remoteDataSource)
    }
}