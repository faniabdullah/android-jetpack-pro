package com.bangkit.faniabdullah_jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

@Dao
interface MovieDao {

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Query("SELECT * FROM tb_movie ")
    fun getListMovieNowPlaying(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_movie where bookmarked = 1")
    fun getBookmarkedMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_movie WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Update
    fun updateTvShows(tvShows: TvShowsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowsEntity>)

    @Query("SELECT * FROM tb_tvShows ")
    fun getListTvShowsPopular(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM tb_tvShows where bookmarked = 1")
    fun getBookmarkedTvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM tb_tvShows WHERE tvShowsId = :tvShowId")
    fun getDetailTvShowsById(tvShowId: Int): LiveData<TvShowsEntity>

}