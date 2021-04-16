package com.bangkit.faniabdullah_jetpack.data.network

import com.bangkit.faniabdullah_jetpack.BuildConfig
import com.bangkit.faniabdullah_jetpack.model.ResultMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ) : Call<ResultMovieResponse>
}