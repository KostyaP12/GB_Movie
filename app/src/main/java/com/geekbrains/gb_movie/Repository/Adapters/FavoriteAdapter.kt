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
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.FavoriteRepository
import com.geekbrains.gb_movie.Repository.FavoriteRepositoryImpl
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Model.MovieFull
import com.geekbrains.gb_movie.showSnackBar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FavoriteAdapter(
        var onItemViewClickListener: OnItemViewClickListener
) : RecyclerView.Adapter<FavoriteAdapter.RecyclerItemViewHolder>() {

    private var data: List<MovieFull> = arrayListOf()
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl(App.getFavoriteDao())


    fun saveFavoriteToDB(movie: MovieFull) {
        favoriteRepository.saveEntity(movie)
    }

    fun findFavorite(id: Int): Int {
        return favoriteRepository.getFavoriteMovie(id)
    }

    fun deleteFavoriteFromDB(id: Int) {
        favoriteRepository.deleteEntity(id)
    }

    fun setData(data: List<MovieFull>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_favorite, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieFull) {
            itemView.apply {
                findViewById<TextView>(R.id.textName).text = movie.title
                findViewById<TextView>(R.id.textReleaseDate).text = movie.release_date.take(4)
                findViewById<TextView>(R.id.textOverview).text = movie.overview
                val poster: ImageView = findViewById(R.id.imageMovie)
                Picasso.get().load("$${Constants.API_IMAGE_URL}${movie.poster_path}")
                        .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                        .into(poster)

                setOnClickListener {
                    val movie = Movie(
                        movie.id,
                        movie.original_title,
                        movie.overview,
                        movie.poster_path,
                        movie.release_date,
                        movie.title
                    )
                    onItemViewClickListener.onItemClick(movie)
                }

                val like: ImageView = findViewById(R.id.like)
                when (findFavorite(movie.id)) {
                    0 -> {
                        like.setImageResource(R.drawable.ic_sharp_favorite_border_24)
                        like.tag = R.string.no_like
                    }
                    1 -> {
                        like.setImageResource(R.drawable.ic_baseline_favorite_24)
                        like.tag = R.string.like
                    }
                }
                lateinit var snackbar: Snackbar

                like.setOnClickListener {

                    when (like.tag) {
                        R.string.no_like -> {
                            like.setImageResource(R.drawable.ic_baseline_favorite_24)
                            like.tag = R.string.like
                            saveFavoriteToDB(movie)
                            snackbar =
                                    showSnackBar(R.string.add_to_favorites, Snackbar.LENGTH_SHORT)
                        }
                        else -> {
                            like.setImageResource(R.drawable.ic_sharp_favorite_border_24)
                            like.tag = R.string.no_like
                            deleteFavoriteFromDB(movie.id)
                            snackbar =
                                    showSnackBar(R.string.remove_from_favorites, Snackbar.LENGTH_SHORT)
                        }
                    }
                    @SuppressLint("InflateParams") val customSnackView: View =
                            LayoutInflater.from(context).inflate(R.layout.rounded, null)
                    snackbar.view.setBackgroundColor(Color.TRANSPARENT)
                    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

                    snackbarLayout.setPadding(R.dimen._20sdp, R.dimen._20sdp, R.dimen._20sdp, R.dimen._20sdp)
                    snackbarLayout.addView(customSnackView, 0)
                    snackbar.show()
                }

            }


        }
    }

}