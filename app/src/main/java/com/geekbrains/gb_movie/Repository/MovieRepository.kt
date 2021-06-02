package com.geekbrains.gb_movie.Repository

import androidx.lifecycle.MutableLiveData
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.Repository.Model.MovieResponse
import com.geekbrains.gb_movie.Repository.RemoteDataSource.ApiClient
import com.geekbrains.gb_movie.Repository.RemoteDataSource.ApiService
import retrofit2.Call
import retrofit2.Response

class MovieRepository{
    private var apiService = ApiClient.api

    fun getPopularMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getPopular(Constants.API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value=null
            }
        })
    }

    fun getNowPlayingMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getLookNow(Constants.API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value=null
            }
        })
    }

    fun getUpComingMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getUpComing(Constants.API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value=null
            }
        })

    }

    fun getTopRatingMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getUpComing(Constants.API_KEY, "ru").enqueue(object :
                retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value=null
            }
        })

    }
}