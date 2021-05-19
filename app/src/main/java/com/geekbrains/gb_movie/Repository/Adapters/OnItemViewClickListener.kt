package com.geekbrains.gb_movie.Repository.Adapters

import com.geekbrains.gb_movie.Repository.Model.Movie

interface OnItemViewClickListener {
    fun onItemClick(movie: Movie)
}