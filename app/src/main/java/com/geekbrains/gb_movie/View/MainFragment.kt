package com.geekbrains.gb_movie.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.Adapters.HorizontalRecyclerAdapter
import com.geekbrains.gb_movie.Repository.Adapters.MovieFragmentDirections
import com.geekbrains.gb_movie.Repository.Adapters.OnItemViewClickListener
import com.geekbrains.gb_movie.Repository.AppState
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.ViewModel.MainViewModel
import com.geekbrains.gb_movie.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private var bundle: Bundle = Bundle()
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val movieAdapter by lazy {
        HorizontalRecyclerAdapter(object : OnItemViewClickListener {
            override fun onItemClick(movie: Movie) {
                bundle.putInt(Constants.BUNDLE_MOVIE_ID, movie.id)
                findNavController().navigate(R.id.infoFragment, bundle)
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initialisation() {
        binding.apply {
            mainRecycler.adapter = movieAdapter
            lookingRecycler.adapter = movieAdapter
            upcomingRecycler.adapter = movieAdapter
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                movieAdapter.setMovie(appState.movieData)
                initialisation()
                setUpLiveData()
                binding.apply {
                    loadingPopular.hide()
                    loadingLookingNow.hide()
                    loadingUpComing.hide()
                }
            }
            is AppState.Loading -> {
                binding.apply {
                    loadingPopular.show()
                    loadingLookingNow.show()
                    loadingUpComing.show()
                }
            }
            is AppState.Error -> {
                binding.apply {
                    loadingPopular.hide()
                    loadingLookingNow.hide()
                    loadingUpComing.hide()
                }
                Snackbar
                        .make(binding.mainFragmentView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.reload)) { viewModel.getMovieFromLocalSource() }
                        .show()
            }
        }
    }

    private fun setUpLiveData() {
        viewModel.apply {
            liveDataPopular.observe(viewLifecycleOwner, { binding.textView2.text = it })
            liveDataNowPlaying.observe(viewLifecycleOwner, { binding.textLookNow.text = it })
            liveDataUpComing.observe(viewLifecycleOwner, { binding.textUpComingNow.text = it })
        }
    }

    private fun View.show():View{
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }
    fun View.hide() : View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }


}