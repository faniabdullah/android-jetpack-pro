package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData
import com.bangkit.faniabdullah_jetpack.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var selectedMovie: String

    fun setSelectedMovie(movieId: String) {
        this.selectedMovie = movieId
    }


    private fun getListMovieNowPlaying(): ArrayList<MovieData> =
        DataDummy.generateDummyDataMovieNowPlaying() as ArrayList<MovieData>

    private fun getListTvShowPopular(): ArrayList<MovieData> =
        DataDummy.generateDummyDataTvShowsPopular() as ArrayList<MovieData>


    fun getDetailMovieById(): MovieData {
        lateinit var result: MovieData
        val listMovie = getListMovieNowPlaying()
        for (movie in listMovie) {
            if (movie.id == selectedMovie) {
                result = movie
                break
            }
        }
        return result
    }

    fun getDetailTvShowById(): MovieData {
        val listTvShow = getListTvShowPopular()
        lateinit var result: MovieData
        for (tvShow in listTvShow) {
            if (tvShow.id == selectedMovie) {
                result = tvShow
                break
            }
        }
        return result
    }
}