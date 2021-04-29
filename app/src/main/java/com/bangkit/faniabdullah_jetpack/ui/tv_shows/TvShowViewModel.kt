package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieMovieRepository
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

class TvShowViewModel(private val mCatalogMovieRepository: CatalogMovieMovieRepository) :
    ViewModel() {

    fun getTvShowsPopular(): LiveData<List<MovieData>> = mCatalogMovieRepository.getPopularTvShows()
}