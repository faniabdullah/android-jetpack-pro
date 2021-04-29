package com.bangkit.faniabdullah_jetpack.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieMovieRepository
import com.bangkit.faniabdullah_jetpack.di.Injection
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailViewModel
import com.bangkit.faniabdullah_jetpack.ui.movie.MovieViewModel
import com.bangkit.faniabdullah_jetpack.ui.tv_shows.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogMovieMovieRepository: CatalogMovieMovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideCatalogRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogMovieMovieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogMovieMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogMovieMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}