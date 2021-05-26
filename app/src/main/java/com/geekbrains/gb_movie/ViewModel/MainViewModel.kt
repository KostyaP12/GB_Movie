package com.geekbrains.gb_movie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.gb_movie.Repository.AppState
import com.geekbrains.gb_movie.Repository.Model.MoviePOJO
import com.geekbrains.gb_movie.Repository.MovieRepository
import com.geekbrains.gb_movie.Repository.Repository
import com.geekbrains.gb_movie.Repository.RepositoryImpl

class MainViewModel() : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _observingMoviesPopular = MutableLiveData<MoviePOJO>()
    fun getObservedMoviesPopular() = _observingMoviesPopular

    private val _observingMoviesLookNow = MutableLiveData<MoviePOJO>()
    fun getObservedMoviesLookNow() = _observingMoviesLookNow


    private val _observingMoviesUpComing = MutableLiveData<MoviePOJO>()
    fun getObservedMoviesUpComing() = _observingMoviesUpComing

    fun popularMovie() {
        movieRepository.getPopularMovies(_observingMoviesPopular)
    }

    fun lookNowMovie() {
        movieRepository.getLookNowMovies(_observingMoviesLookNow)
    }

    fun upComingMovie() {
        movieRepository.getUpComingMovies(_observingMoviesUpComing)
    }
    val liveDataPopular = MutableLiveData<String>()
    val liveDataNowPlaying = MutableLiveData<String>()
    val liveDataUpComing = MutableLiveData<String>()
    init {
        setPopular()
        setNowPlaying()
        setUpComing()
    }

    private fun setPopular() {
        liveDataPopular.value = "Popular"
    }

    private fun setNowPlaying() {
        liveDataNowPlaying.value = "Now Playing"
    }

    private fun setUpComing() {
        liveDataUpComing.value = "Up Coming"
    }
}