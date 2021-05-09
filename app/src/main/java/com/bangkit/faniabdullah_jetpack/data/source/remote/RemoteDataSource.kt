package com.bangkit.faniabdullah_jetpack.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.faniabdullah_jetpack.data.source.remote.network.ApiConfig
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.CatalogResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.vo.ApiResponse
import com.bangkit.faniabdullah_jetpack.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply {
                    instance = this
                }
            }
    }

    fun getMovieNowPlaying(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()

        val resultResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        ApiConfig.getApiService().getMovieNowPlaying()
            .enqueue(object : Callback<CatalogResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<CatalogResponse<MovieResponse>>,
                    response: Response<CatalogResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        resultResponse.postValue(ApiResponse.success(response.body()?.results as List<MovieResponse>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<CatalogResponse<MovieResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    resultResponse.postValue(
                        ApiResponse.error(
                            t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return resultResponse
    }

    fun getTvShowPopular(): LiveData<ApiResponse<List<TvShowsResponse>>> {
        EspressoIdlingResource.increment()
        val resultResponse = MutableLiveData<ApiResponse<List<TvShowsResponse>>>()
        ApiConfig.getApiService().getPopularTvShows()
            .enqueue(object : Callback<CatalogResponse<TvShowsResponse>> {
                override fun onResponse(
                    call: Call<CatalogResponse<TvShowsResponse>>,
                    response: Response<CatalogResponse<TvShowsResponse>>
                ) {
                    if (response.isSuccessful) {
                        resultResponse.postValue(ApiResponse.success(response.body()?.results as List<TvShowsResponse>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<CatalogResponse<TvShowsResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    resultResponse.postValue(
                        ApiResponse.error(
                            t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }
            })
        return resultResponse
    }


}