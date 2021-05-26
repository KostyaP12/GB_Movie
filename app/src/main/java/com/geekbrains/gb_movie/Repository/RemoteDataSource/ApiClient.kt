package com.geekbrains.gb_movie.Repository.RemoteDataSource

import com.geekbrains.gb_movie.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.basicURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api by lazy {
        retrofit.create(ApiService::class.java)
    }
}
