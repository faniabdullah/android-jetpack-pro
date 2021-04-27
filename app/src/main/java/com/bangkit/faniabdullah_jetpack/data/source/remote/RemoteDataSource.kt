package com.bangkit.faniabdullah_jetpack.data.source.remote

import android.content.Context
import com.bangkit.faniabdullah_jetpack.data.source.remote.network.ApiConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.DetailMovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.DetailTvResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMovieNowPlaying(
        callback: LoadMoviesNowPlayingCallback
    ) {
        EspressoIdlingResource.increment()
       ApiConfig.instance.getMovieNowPlaying().await().results?.let { listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowPopular(callback: LoadPopularTvShowCallback) {
        EspressoIdlingResource.increment()
       ApiConfig.instance.getPopularTvShows().await().results?.let { listTvShow ->
            callback.onAllTvShowsReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

       suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
       EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailMovie(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
       ApiConfig.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }


    interface LoadMoviesNowPlayingCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse?>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: DetailMovieResponse)
    }

    interface LoadPopularTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowsResponse?>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: DetailTvResponse)
    }

}