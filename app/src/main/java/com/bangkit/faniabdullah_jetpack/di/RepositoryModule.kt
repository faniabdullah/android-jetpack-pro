package com.bangkit.faniabdullah_jetpack.di

import com.bangkit.faniabdullah_jetpack.data.CatalogMovieDataSource
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import dagger.Binds
import dagger.Module


@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract  class RepositoryModule {

    @Binds
    abstract fun provideRepository(catalogMovieRepository: CatalogMovieRepository) : CatalogMovieDataSource
}