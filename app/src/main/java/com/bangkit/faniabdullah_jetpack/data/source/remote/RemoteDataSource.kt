package com.bangkit.faniabdullah_jetpack.data.source.remote

import android.util.Log
import com.bangkit.faniabdullah_jetpack.data.source.remote.network.ApiConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.CatalogResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.DetailMovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.DetailTvResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
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
            .enqueue(object : Callback<CatalogResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<CatalogResponse<MovieResponse>>,
                    response: Response<CatalogResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.results?.let { callback.onAllMoviesReceived(it) }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<CatalogResponse<MovieResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    callback.onAllMoviesReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })

    }


    fun getTvShowPopular(callback: LoadPopularTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getPopularTvShows()
            .enqueue(object : Callback<CatalogResponse<TvShowsResponse>> {
                override fun onResponse(
                    call: Call<CatalogResponse<TvShowsResponse>>,
                    response: Response<CatalogResponse<TvShowsResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.results?.let { callback.onAllTvShowsReceived(it) }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<CatalogResponse<TvShowsResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    callback.onAllTvShowsReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailMovie(movieId)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback.onMovieDetailReceived(it) }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.e("Failure", "${t.message}")

                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailTvShow(tvShowId)
            .enqueue(object : Callback<DetailTvResponse> {
                override fun onResponse(
                    call: Call<DetailTvResponse>,
                    response: Response<DetailTvResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback.onTvShowDetailReceived(it) }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    EspressoIdlingResource.decrement()
                }
            })
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