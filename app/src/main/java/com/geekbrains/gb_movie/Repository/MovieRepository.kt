package com.geekbrains.gb_movie.Repository

import androidx.lifecycle.MutableLiveData
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.Repository.Model.MoviePOJO
import com.geekbrains.gb_movie.Repository.RemoteDataSource.ApiClient
import com.geekbrains.gb_movie.Repository.RemoteDataSource.ApiService
import retrofit2.Call
import retrofit2.Response

class MovieRepository{
    private var apiService: ApiService = ApiClient.api

    fun getPopularMovies(_observingMovies: MutableLiveData<MoviePOJO>) {
        apiService.getPopular(Constants.API_KEY, Constants.locale).enqueue(object :
            retrofit2.Callback<MoviePOJO> {
            override fun onResponse(call: Call<MoviePOJO>, response: Response<MoviePOJO>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MoviePOJO>, t: Throwable) {
                _observingMovies.value=null
            }
        })
    }

    fun getLookNowMovies(_observingMovies: MutableLiveData<MoviePOJO>) {
        apiService.getLookNow(Constants.API_KEY, Constants.locale).enqueue(object :
            retrofit2.Callback<MoviePOJO> {
            override fun onResponse(call: Call<MoviePOJO>, response: Response<MoviePOJO>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MoviePOJO>, t: Throwable) {
                _observingMovies.value=null
            }
        })
    }

    fun getUpComingMovies(_observingMovies: MutableLiveData<MoviePOJO>) {
        apiService.getUpComing(Constants.API_KEY, Constants.locale).enqueue(object :
            retrofit2.Callback<MoviePOJO> {
            override fun onResponse(call: Call<MoviePOJO>, response: Response<MoviePOJO>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MoviePOJO>, t: Throwable) {
                _observingMovies.value=null
            }
        })

    }
}