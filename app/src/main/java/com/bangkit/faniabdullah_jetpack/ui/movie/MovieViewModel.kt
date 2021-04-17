package com.bangkit.faniabdullah_jetpack.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.network.RetrofitClient
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.model.ResultMovieResponse
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    val listMovieNowPlaying = MutableLiveData<ArrayList<MovieEntity>>()
    val isSuccess = MutableLiveData<Boolean>()

    fun setMovieNowPlaying() {
        RetrofitClient.apiInstance
            .getMovieNowPlaying()
            .enqueue(object : Callback<ResultMovieResponse> {
                override fun onResponse(
                    call: Call<ResultMovieResponse>,
                    response: Response<ResultMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        isSuccess.postValue(true)
                        listMovieNowPlaying.postValue(response.body()?.results)
                    }
                }

                override fun onFailure(call: Call<ResultMovieResponse>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    isSuccess.postValue(false)
                }
            })
    }


    fun getMovieNowPlaying(): LiveData<ArrayList<MovieEntity>> {
        return listMovieNowPlaying
    }

    fun checkStatusServer(): MutableLiveData<Boolean> {
        return isSuccess
    }

    fun getMovieNowPlayingNewsVersion(): List<MovieEntity> = DataDummy.generateDummyDataMovieNowPlaying()
}