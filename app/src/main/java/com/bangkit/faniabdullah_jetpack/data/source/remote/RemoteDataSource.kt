package com.bangkit.faniabdullah_jetpack.data.source.remote

import android.util.Log
import com.bangkit.faniabdullah_jetpack.data.source.remote.network.ApiConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.*
import com.bangkit.faniabdullah_jetpack.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovieNowPlaying(
        callback: LoadMoviesNowPlayingCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getMovieNowPlaying()
            .enqueue(object : Callback<ParentResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<ParentResponse<MovieResponse>>,
                    response: Response<ParentResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.results?.let { callback.onAllMoviesReceived(it) }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ParentResponse<MovieResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })

    }


    fun getTvShowPopular(callback: LoadPopularTvShowCallback) {
        ApiConfig.getApiService().getPopularTvShows()
            .enqueue(object : Callback<ParentResponse<TvShowsResponse>> {
                override fun onResponse(
                    call: Call<ParentResponse<TvShowsResponse>>,
                    response: Response<ParentResponse<TvShowsResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.results?.let { callback.onAllTvShowsReceived(it) }
                    }
                }

                override fun onFailure(call: Call<ParentResponse<TvShowsResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                }
            })
    }

    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()

    }

    fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()

        EspressoIdlingResource.decrement()
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
        fun onTvShowDetailReceived(tvShowResponse: Call<DetailTvResponse>)
    }

}