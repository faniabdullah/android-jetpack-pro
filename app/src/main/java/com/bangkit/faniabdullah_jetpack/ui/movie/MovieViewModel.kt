package com.bangkit.faniabdullah_jetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogRepository
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

class MovieViewModel(private val mCatalogRepository: CatalogRepository) : ViewModel() {

    fun getMovieNowPlaying(): LiveData<List<MovieData>> = mCatalogRepository.getMovieNowPlaying()
}