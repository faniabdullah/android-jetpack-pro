package com.bangkit.faniabdullah_jetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieMovieRepository
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

class MovieViewModel(private val mCatalogMovieRepository: CatalogMovieMovieRepository) : ViewModel() {

    fun getMovieNowPlaying(): LiveData<List<MovieData>> = mCatalogMovieRepository.getMovieNowPlaying()
}