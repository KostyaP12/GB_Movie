package com.geekbrains.gb_movie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.gb_movie.Repository.Model.MovieResponse

class RatingViewModel : ViewModel() {
    private val _observingTopRating = MutableLiveData<MovieResponse>()
    fun getTopRatingMovies() = _observingTopRating
}