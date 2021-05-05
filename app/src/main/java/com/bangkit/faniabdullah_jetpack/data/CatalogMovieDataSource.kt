package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.utils.vo.Resource

interface CatalogMovieDataSource {
    fun getMovieNowPlaying(): LiveData<Resource<List<MovieEntity>>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShowsEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowsEntity>

    fun setFavoriteTvShows(tvShow: TvShowsEntity, state: Boolean)

    fun getFavoritesTvShows() : LiveData<List<TvShowsEntity>>

    fun setFavoriteMovies(movie: MovieEntity, state: Boolean)

    fun getFavoritesMovies() : LiveData<List<MovieEntity>>

}