package com.bangkit.faniabdullah_jetpack.data.source.remote.network

import com.bangkit.faniabdullah_jetpack.BuildConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ): Call<ParentResponse<MovieResponse>>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ): Call<ParentResponse<TvShowsResponse>>



    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ): Call<DetailMovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ): Call<DetailTvResponse>
}