package com.bangkit.faniabdullah_jetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mMovieDao: MovieDao) {

    fun getAllMoviesNowPlaying(): DataSource.Factory<Int, MovieEntity> =
        mMovieDao.getListMovieNowPlaying()

    fun getAllFavoritesMovie(): DataSource.Factory<Int, MovieEntity> =
        mMovieDao.getBookmarkedMovie()

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

    fun getAllTvShowsPopular(): DataSource.Factory<Int, TvShowsEntity> =
        mMovieDao.getListTvShowsPopular()

    fun getAllFavoritesTvShows(): DataSource.Factory<Int, TvShowsEntity> =
        mMovieDao.getBookmarkedTvShows()

    fun getTvShowsById(tvShowsId: Int): LiveData<TvShowsEntity> =
        mMovieDao.getDetailTvShowsById(tvShowsId)
}