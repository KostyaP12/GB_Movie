package com.geekbrains.gb_movie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.gb_movie.Repository.AppState
import com.geekbrains.gb_movie.Repository.Repository
import com.geekbrains.gb_movie.Repository.RepositoryImpl

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataToObserve

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

    fun getMovieFromLocalSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getMovieFromLocalServer()))
        }.start()
    }
}