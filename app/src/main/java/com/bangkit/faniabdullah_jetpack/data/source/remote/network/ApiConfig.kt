package com.bangkit.faniabdullah_jetpack.data.source.remote.network

import com.bangkit.faniabdullah_jetpack.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }


        private val httpClient = OkHttpClient.Builder().apply {

        }.build()


        private val retrofit: Retrofit.Builder by lazy {
            Retrofit.Builder().apply {
                client(httpClient)
                baseUrl(Constant.BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
            }
        }


        val instance: ApiService by lazy {
            retrofit
                .build()
                .create(ApiService::class.java)
        }
    }
}