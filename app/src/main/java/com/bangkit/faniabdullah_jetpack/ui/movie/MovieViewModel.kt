package com.bangkit.faniabdullah_jetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.vo.Resource

class MovieViewModel(private val mCatalogMovieRepository: CatalogMovieRepository) :
    ViewModel() {

    fun getMovieNowPlaying(): LiveData<Resource<List<MovieEntity>>> =
        mCatalogMovieRepository.getMovieNowPlaying()
}