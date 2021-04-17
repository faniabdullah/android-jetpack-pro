package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var selectedMovie: String

    fun setSelectedMovie(movieId: String) {
        this.selectedMovie = movieId
    }


    private fun getListMovieNowPlaying(): ArrayList<MovieEntity> =
        DataDummy.generateDummyDataMovieNowPlaying() as ArrayList<MovieEntity>

    private fun getListTvShowPopular(): ArrayList<MovieEntity> =
        DataDummy.generateDummyDataTvShowsPopular() as ArrayList<MovieEntity>


    fun getDetailMovieById(): MovieEntity {
        lateinit var result: MovieEntity
        val listMovie = getListMovieNowPlaying()
        for (movie in listMovie) {
            if (movie.id == selectedMovie) {
                result = movie
                break
            }
        }
        return result
    }

    fun getDetailTvShowById(): MovieEntity {
        val listTvShow = getListTvShowPopular()
        lateinit var result: MovieEntity
        for (tvShow in listTvShow) {
            if (tvShow.id == selectedMovie) {
                result = tvShow
                break
            }
        }
        return result
    }
}