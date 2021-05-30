package com.geekbrains.gb_movie.Repository.Adapters

import android.os.Bundle
import androidx.navigation.NavDirections
import com.geekbrains.gb_movie.R
import kotlin.Int

class MovieFragmentDirections () {
    data class OpenMovie(
             val movieId: Int
    ) : NavDirections {
       override fun getActionId(): Int = R.id.openMovie

       override fun getArguments(): Bundle {
            val result = Bundle()
            result.putInt("movieId", this.movieId)
            return result
        }
    }

    companion object {
        public fun openMovie(movieId: Int): NavDirections = OpenMovie(movieId)
    }
}
