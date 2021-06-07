package com.geekbrains.gb_movie.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.Adapters.MovieFragmentDirections
import com.geekbrains.gb_movie.Repository.RepositoryImpl
import com.geekbrains.gb_movie.ViewModel.InfoViewModel
import com.geekbrains.gb_movie.databinding.InfoFragmentBinding
import kotlinx.android.synthetic.main.info_fragment.view.*

class InfoFragment : Fragment() {
    private val repositoryImpl = RepositoryImpl()
    private val infoViewModel: InfoViewModel by lazy { ViewModelProvider(this).get(InfoViewModel::class.java) }
    private var _binding: InfoFragmentBinding? = null
    private val binding: InfoFragmentBinding get() = _binding!!
    private val movieFragmentDirections = MovieFragmentDirections

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        _binding = InfoFragmentBinding.inflate(inflater, container, false)
        val movieId = arguments?.getInt(Constants.BUNDLE_MOVIE_ID)
        val movieFullList = repositoryImpl.getMovieInfoFromLocalServer()
        for (movieFull in movieFullList) {
            when (movieFull.id) {
                movieId -> binding.apply {
                    budget.text = movieFull.budget.toString()
                    title.text = movieFull.original_title.toString()
                    poster.setImageResource(R.drawable.poster)
                    like.setImageResource(R.drawable.ic_sharp_favorite_border_24)
                    overview.text = movieFull.overview
                    releaseDate.text = movieFull.release_date
                    revenue.text = movieFull.revenue.toString()
                    voteAverage.text = movieFull.vote_average.toString()
                    progressBarInfo.visibility = View.GONE
                }
            }
        }
        return binding.root
    }
}