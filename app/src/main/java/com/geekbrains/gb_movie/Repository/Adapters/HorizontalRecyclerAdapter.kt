package com.geekbrains.gb_movie.Repository.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.gb_movie.App
import com.geekbrains.gb_movie.Constants.API_IMAGE_URL
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.FavoriteRepository
import com.geekbrains.gb_movie.Repository.FavoriteRepositoryImpl
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Model.MovieFull
import com.geekbrains.gb_movie.showSnackBar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_film.view.*
import java.util.ArrayList

class HorizontalRecyclerAdapter(var onItemViewClickListener: OnItemViewClickListener) :
        RecyclerView.Adapter<HorizontalRecyclerAdapter.MovieViewHolder>() {

    private var moviesList = arrayListOf<Movie>()
    private val favoriteRepository: FavoriteRepository =
        FavoriteRepositoryImpl(App.getFavoriteDao())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_film, parent, false)
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
                Picasso.get().load("${API_IMAGE_URL}${movie.poster_path}")
                        .placeholder(R.drawable.ic_baseline_image_not_supported_24).into(poster)
                findViewById<TextView>(R.id.textName).text = movie.title
                findViewById<TextView>(R.id.textReleaseDate).text = movie.release_date

                val itemLike = findViewById<ImageView>(R.id.like)
                when (findFavorite(movie.id)) {
                    0 -> {
                        itemLike.setImageResource(R.drawable.ic_sharp_favorite_border_24)
                        itemLike.tag = R.string.no_like
                    }
                    1 -> {
                        itemLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                        itemLike.tag = R.string.like
                    }
                }
                lateinit var snackbar: Snackbar

                itemLike.setOnClickListener {

                    when (itemLike.tag) {
                        R.string.no_like -> {
                            itemLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                            itemLike.tag = R.string.like
                            saveFavoriteToDB(
                                    MovieFull(
                                            movie.id,
                                            "",
                                            movie.overview,
                                            movie.poster_path,
                                            movie.release_date,
                                            movie.title,
                                            0.0,
                                            0,
                                            0,
                                            0,
                                            0,
                                            "",
                                            0.0
                                    )
                            )
                            snackbar =
                                    showSnackBar(R.string.add_to_favorites, Snackbar.LENGTH_SHORT)
                        }
                        else -> {
                            itemLike.setImageResource(R.drawable.ic_sharp_favorite_border_24)
                            itemLike.tag = R.string.no_like
                            deleteFavoriteFromDB(movie.id)
                            snackbar =
                                    showSnackBar(R.string.remove_from_favorites, Snackbar.LENGTH_SHORT)
                        }
                    }
                    @SuppressLint("InflateParams") val customSnackView: View =
                            LayoutInflater.from(context).inflate(R.layout.rounded, null)
                    snackbar.view.setBackgroundColor(Color.TRANSPARENT)
                    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

                    snackbarLayout.setPadding(20, 20, 20, 20)
                    snackbarLayout.addView(customSnackView, 0)
                    snackbar.show()
                }
                setOnClickListener {
                    onItemViewClickListener.onItemClick(movie)
                }
            }
        }
    }

    fun saveFavoriteToDB(movie: MovieFull) {
        favoriteRepository.saveEntity(movie)
    }

    fun findFavorite(id: Int): Int {
        return favoriteRepository.getFavoriteMovie(id)
    }

    fun deleteFavoriteFromDB(id: Int) {
        favoriteRepository.deleteEntity(id)
    }
}
