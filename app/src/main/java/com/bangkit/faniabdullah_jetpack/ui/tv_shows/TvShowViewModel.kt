package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.network.RetrofitClient
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.model.ResultMovieResponse
import com.bangkit.faniabdullah_jetpack.model.ResultTvShowsResponse
import com.bangkit.faniabdullah_jetpack.model.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {

    val listTvShowsPopular = MutableLiveData<ArrayList<TvShowsEntity>>()
    val isSuccess = MutableLiveData<Boolean>()

    fun setPopularTvShows() {
        RetrofitClient.apiInstance
            .getPopularTvShows()
            .enqueue(object : Callback<ResultTvShowsResponse> {
                override fun onResponse(
                    call: Call<ResultTvShowsResponse>,
                    response: Response<ResultTvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        isSuccess.postValue(true)
                        listTvShowsPopular.postValue(response.body()?.results)
                    }
                }

                override fun onFailure(call: Call<ResultTvShowsResponse>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    isSuccess.postValue(false)
                }
            })
    }


    fun getTvShowsPopular(): LiveData<ArrayList<TvShowsEntity>> {
        return listTvShowsPopular
    }

    fun getTvShowsPopularNewsVersion(): List<MovieEntity> = DataDummy.generateDummyDataTvShowsPopular()

    fun checkStatusServer(): MutableLiveData<Boolean> {
        return isSuccess
    }
}