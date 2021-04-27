package com.bangkit.faniabdullah_jetpack.data.source.remote

import android.util.Log
import com.bangkit.faniabdullah_jetpack.data.source.remote.network.ApiConfig
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
        ApiConfig.getApiService().getMovieNowPlaying().await().results?.let { listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowPopular(callback: LoadPopularTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getPopularTvShows().await().results?.let { listTvShow ->
            callback.onAllTvShowsReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesNowPlayingCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse?>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadPopularTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowsResponse?>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShowsResponse)
    }

}