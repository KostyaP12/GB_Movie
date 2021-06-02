package com.geekbrains.gb_movie.Services

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.Repository.Model.MovieResponse
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class TopRatingService(name:String = "TopRatingService"): IntentService(name) {
    private val broadcastIntent = Intent("INTENT FILTER")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) return
        val movie = intent.getStringExtra("Movie")
        if (movie == "") return
        loadData(movie)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadData(movie: String?) {
        try {
            val uri = URL("${Constants.basicURL}${Constants.top_rating}?api_key=${Constants.API_KEY}&language=ru")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = "GET"
                    readTimeout = 10000
                }
                val movieResponse: MovieResponse =
                        Gson().fromJson(
                                getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                                MovieResponse::class.java
                        )
                onResponse(movieResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader) =
            reader.lines().collect(Collectors.joining("\n"))


    private fun onResponse(movieResponse: MovieResponse) {
        broadcastIntent.putExtra("movieResponse", movieResponse)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
}