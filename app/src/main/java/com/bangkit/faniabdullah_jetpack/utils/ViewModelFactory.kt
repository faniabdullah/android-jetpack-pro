package com.bangkit.faniabdullah_jetpack.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.di.AppScope
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailViewModel
import com.bangkit.faniabdullah_jetpack.ui.favorite.FavoriteViewModel
import com.bangkit.faniabdullah_jetpack.ui.movie.MovieViewModel
import com.bangkit.faniabdullah_jetpack.ui.tv_shows.TvShowViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val mCatalogMovieRepository: CatalogMovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(Injection.provideCatalogRepository(context)).apply {
//                    instance = this
//                }
//            }
//    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogMovieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogMovieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mCatalogMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}