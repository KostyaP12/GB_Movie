package com.geekbrains.gb_movie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.gb_movie.Repository.Model.MovieResponse
import com.geekbrains.gb_movie.Repository.MovieRepository

class MainViewModel() : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _observingMoviesPopular = MutableLiveData<MovieResponse>()
    fun getObservedMoviesPopular() = _observingMoviesPopular

    private val _observingMoviesNowPlaying = MutableLiveData<MovieResponse>()
    fun getObservedMoviesNowPlaying() = _observingMoviesNowPlaying

    private val _observingMoviesUpComing = MutableLiveData<MovieResponse>()
    fun getObservedMoviesUpComing() = _observingMoviesUpComing

    fun popularMovie() {
        movieRepository.getPopularMovies(_observingMoviesPopular)
    }

    fun nowPlayingMovie() {
        movieRepository.getNowPlayingMovies(_observingMoviesNowPlaying)
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