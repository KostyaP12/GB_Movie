package com.geekbrains.gb_movie.Repository.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Repository
import com.geekbrains.gb_movie.Repository.RepositoryImpl

class HorizontalRecyclerAdapter(var onItemViewClickListener: OnItemViewClickListener) :
        RecyclerView.Adapter<HorizontalRecyclerAdapter.MovieViewHolder>() {

    private var movieData: List<Movie> = listOf()

    fun setMovie(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_film, parent, false)
    )

    override fun getItemCount() = movieData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movieData[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindMovie(movie: Movie) {
            itemView.apply {
                findViewById<ImageView>(R.id.imageMovie).setImageResource(R.drawable.poster)
                findViewById<TextView>(R.id.textName).text = movie.title
                findViewById<TextView>(R.id.textReleaseDate).text = movie.release_date
                setOnClickListener{
                    onItemViewClickListener.onItemClick(movie)
                }
            }
        }
    }
}
