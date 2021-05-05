package com.bangkit.faniabdullah_jetpack.data.source.local

import androidx.lifecycle.LiveData
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mMovieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mMovieDao).apply {
                INSTANCE = this
            }
    }

    fun getAllMoviesNowPlaying(): LiveData<List<MovieEntity>> = mMovieDao.getListMovieNowPlaying()

    fun getAllFavoritesMovie(): LiveData<List<MovieEntity>> = mMovieDao.getBookmarkedMovie()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = mMovieDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)


    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieDao.updateMovie(movie)
    }


    fun insertTvShows(tvShows: List<TvShowsEntity>) = mMovieDao.insertTvShows(tvShows)

    fun setFavoriteTvShows(tvShows: TvShowsEntity, newState: Boolean) {
        tvShows.favorite = newState
        mMovieDao.updateTvShows(tvShows)
    }

    fun getAllTvShowsPopular(): LiveData<List<TvShowsEntity>> = mMovieDao.getListTvShowsPopular()

    fun getAllFavoritesTvShows(): LiveData<List<TvShowsEntity>> = mMovieDao.getBookmarkedTvShows()

    fun getTvShowsById(tvShowsId: Int): LiveData<TvShowsEntity> =
        mMovieDao.getDetailTvShowsById(tvShowsId)
}