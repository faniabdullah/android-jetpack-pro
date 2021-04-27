package com.bangkit.faniabdullah_jetpack.di

import android.content.Context
import com.bangkit.faniabdullah_jetpack.data.CatalogRepository
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}