package com.bangkit.faniabdullah_jetpack.data.source.remote.network

import com.bangkit.faniabdullah_jetpack.BuildConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.CatalogResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ): Call<CatalogResponse<MovieResponse>>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ): Call<CatalogResponse<TvShowsResponse>>


}