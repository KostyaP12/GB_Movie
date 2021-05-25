package com.geekbrains.gb_movie.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Repository.Adapters.HorizontalRecyclerAdapter
import com.geekbrains.gb_movie.Repository.AppState
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.ViewModel.MainViewModel
import com.geekbrains.gb_movie.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieFromLocalSource()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initialisation(movieData:ArrayList<Movie>) {
        binding.mainRecycler.apply { adapter = HorizontalRecyclerAdapter(movieData) }
        binding.lookingRecycler.apply { adapter = HorizontalRecyclerAdapter(movieData)}
        binding.upcomingRecycler.apply { adapter = HorizontalRecyclerAdapter(movieData)}
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movieData = appState.movieData
                initialisation(movieData)
                setUpLiveData()
                binding.loadingPopular.visibility = View.GONE
                binding.loadingLookingNow.visibility = View.GONE
                binding.loadingUpComing.visibility = View.GONE

            }
            is AppState.Loading -> {
                binding.loadingPopular.visibility = View.VISIBLE
                binding.loadingLookingNow.visibility = View.VISIBLE
                binding.loadingUpComing.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingPopular.visibility = View.GONE
                binding.loadingLookingNow.visibility = View.GONE
                binding.loadingUpComing.visibility = View.GONE
                Snackbar
                    .make(binding.mainFragmentView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getMovieFromLocalSource() }
                    .show()
            }
        }
    }
    private fun setUpLiveData() {
        viewModel.liveDataPopular.observe(viewLifecycleOwner, { binding.textView2.text = it })
        viewModel.liveDataNowPlaying.observe(viewLifecycleOwner, { binding.textLookNow.text = it })
        viewModel.liveDataUpComing.observe(viewLifecycleOwner, { binding.textUpComingNow.text = it })

    }

}