package com.geekbrains.gb_movie.Repository.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.squareup.picasso.Picasso
import java.util.ArrayList

class VerticalRecyclerAdapter(var onItemViewClickListener: OnItemViewClickListener) :
        RecyclerView.Adapter<VerticalRecyclerAdapter.MovieViewHolder>() {

    private var moviesList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_top_film, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(moviesList[position])
    }

    fun addItems(moviesList: ArrayList<Movie>) {
        this.moviesList.addAll(moviesList)
    }

    fun clearItems() = this.moviesList.clear()


    override fun getItemCount() = moviesList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMovie(movie: Movie) {
            itemView.apply {
                val poster: ImageView = findViewById(R.id.imageMovie)
                Picasso.get().load("${Constants.API_IMAGE_URL}${movie.poster_path}")
                    .placeholder(R.drawable.ic_baseline_image_not_supported_24).into(poster)
                findViewById<TextView>(R.id.textName).text = movie.title
                findViewById<TextView>(R.id.textReleaseDate).text = movie.release_date
                setOnClickListener{
                    onItemViewClickListener.onItemClick(movie)
                }
            }
        }
    }
}
