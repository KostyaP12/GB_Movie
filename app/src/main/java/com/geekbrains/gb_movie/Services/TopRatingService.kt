package com.geekbrains.gb_movie.Services

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.Repository.Model.MovieResponse
import com.geekbrains.gb_movie.Repository.RemoteDataSource.ApiClient
import retrofit2.Call
import retrofit2.Response

class TopRatingService(name: String = "TopRatingService") : IntentService(name) {
    private val broadcastIntent = Intent("INTENT FILTER")
    private var apiService = ApiClient.api

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) return
        val movie = intent.getStringExtra("Movie")
        if (movie == "") return
        getTopRatingMovies()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getTopRatingMovies() {
        apiService.getUpComing(Constants.API_KEY, "ru").enqueue(object :
                retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    sendBack(result = response.body()!!)

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println("NULL")
            }
        })

    }
    private fun sendBack(result: MovieResponse) {
        broadcastIntent.putExtra("movieResponse", result)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
}