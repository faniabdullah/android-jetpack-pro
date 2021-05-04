package com.bangkit.faniabdullah_jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

@Dao
interface MovieDao {

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(courses: List<MovieEntity>)

    @Query("SELECT * FROM tb_movie ")
    fun getListMovieNowPlaying(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tb_movie where bookmarked = 1")
    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tb_movie WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Update
    fun updateTvShows(tvShows: TvShowsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(courses: List<TvShowsEntity>)

    @Query("SELECT * FROM tb_tvshows ")
    fun getListTvShowsPopular(): LiveData<List<TvShowsEntity>>

    @Query("SELECT * FROM tb_tvshows where bookmarked = 1")
    fun getBookmarkedTvShows(): LiveData<List<TvShowsEntity>>

    @Query("SELECT * FROM tb_tvshows WHERE tvShowsId = :tvShowId")
    fun getDetailTvShowsById(tvShowId: Int): LiveData<TvShowsEntity>

}