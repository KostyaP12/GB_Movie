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
        initialisation()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initialisation() {
        binding.apply {
            mainRecycler.adapter = movieAdapter
            lookingRecycler.adapter = movieAdapter
            upcomingRecycler.adapter = movieAdapter
        }
        setUpLiveData()
        getPopularMovies()
        //getLookNowMovies()
        //getUpComingMovies()
    }

    private fun setUpLiveData() {
        viewModel.apply {
            liveDataPopular.observe(viewLifecycleOwner, { binding.textView2.text = it })
            liveDataNowPlaying.observe(viewLifecycleOwner, { binding.textLookNow.text = it })
            liveDataUpComing.observe(viewLifecycleOwner, { binding.textUpComingNow.text = it })
        }
    }

    private fun View.show(): View {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }

    fun View.hide(): View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }

    private fun getPopularMovies() {
        binding.loadingPopular.show()
        viewModel.apply {
            getObservedMoviesPopular().observe(viewLifecycleOwner, { it ->
                movieAdapter.clearItems()
                movieAdapter.addItems(it.results)
                movieAdapter.notifyDataSetChanged()
                binding.loadingPopular.hide()
            })
            popularMovie()
        }
    }


    /*private fun getLookNowMovies() {
        binding.loadingLookingNow.show()
        viewModel.apply {
            getObservedMoviesLookNow().observe(viewLifecycleOwner, { it ->
                movieAdapter.clearItems()
                it.result.let {
                    movieAdapter.addItems(it)
                    movieAdapter.notifyDataSetChanged()
                    binding.loadingLookingNow.hide()
                }
            })
            lookNowMovie()
        }
    }

    private fun getUpComingMovies() {
        binding.loadingUpComing.show()
        viewModel.apply {
            getObservedMoviesUpComing().observe(viewLifecycleOwner, { it ->
                movieAdapter.clearItems()
                it.result.let {
                    movieAdapter.addItems(it)
                    movieAdapter.notifyDataSetChanged()
                    binding.loadingUpComing.hide()
                }
            })
            upComingMovie()
        }
    }*/


}