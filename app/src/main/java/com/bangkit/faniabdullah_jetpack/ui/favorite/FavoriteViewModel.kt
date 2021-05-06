package com.bangkit.faniabdullah_jetpack.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

class FavoriteViewModel(private val mCatalogMovieRepository: CatalogMovieRepository) : ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> =
        mCatalogMovieRepository.getFavoritesMovies()

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowsEntity>> =
        mCatalogMovieRepository.getFavoritesTvShows()

}