package com.bangkit.faniabdullah_jetpack.ui.movie

import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovieNowPlaying(): List<MovieEntity> =
        DataDummy.generateDummyDataMovieNowPlaying()
}