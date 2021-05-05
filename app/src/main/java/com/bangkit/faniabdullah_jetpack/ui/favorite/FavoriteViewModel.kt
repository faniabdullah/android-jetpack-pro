package com.bangkit.faniabdullah_jetpack.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

class FavoriteViewModel(private val mCatalogMovieRepository: CatalogMovieRepository) : ViewModel() {
    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = mCatalogMovieRepository.getFavoritesMovies()

    fun getFavoriteTvShows(): LiveData<List<TvShowsEntity>> = mCatalogMovieRepository.getFavoritesTvShows()

}