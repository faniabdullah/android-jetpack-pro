package com.bangkit.faniabdullah_jetpack.data.source.remote.network

import com.bangkit.faniabdullah_jetpack.BuildConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.ParentResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ) : Call<ParentResponse<MovieResponse>>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ) : Call<ParentResponse<TvShowsResponse>>
}