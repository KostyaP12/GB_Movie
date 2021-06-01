package com.geekbrains.gb_movie.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Receivers.MyBroadcastReceiver
import com.geekbrains.gb_movie.Repository.Adapters.HorizontalRecyclerAdapter
import com.geekbrains.gb_movie.Repository.Adapters.OnItemViewClickListener
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.ViewModel.MainViewModel
import com.geekbrains.gb_movie.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private var bundle: Bundle = Bundle()
    private val binding get() = _binding!!
    private val myBroadcastReceiver: MyBroadcastReceiver = MyBroadcastReceiver()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val popularMovieAdapter by lazy {
        HorizontalRecyclerAdapter(object : OnItemViewClickListener {
            override fun onItemClick(movie: Movie) {
                bundle.putInt(Constants.BUNDLE_MOVIE_ID, movie.id)
                findNavController().navigate(R.id.infoFragment, bundle)
            }
        })
    }

    private val nowPlayingMovieAdapter by lazy {
        HorizontalRecyclerAdapter(object : OnItemViewClickListener {
            override fun onItemClick(movie: Movie) {
                bundle.putInt(Constants.BUNDLE_MOVIE_ID, movie.id)
                findNavController().navigate(R.id.infoFragment, bundle)
            }
        })
    }

    private val upComingMovieAdapter by lazy {
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
        when (myBroadcastReceiver.checkInternet(requireContext())) {
            false -> {
                Toast.makeText(requireContext(), "Нет соединения с интернетом", Toast.LENGTH_LONG).show()
            }
            true ->{
                binding.apply {
                    mainRecycler.adapter = popularMovieAdapter
                    lookingRecycler.adapter = nowPlayingMovieAdapter
                    upcomingRecycler.adapter = upComingMovieAdapter
                }
                setUpLiveData()
                getPopularMovies()
                getNowPlayingMovies()
                getUpComingMovies()
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
                popularMovieAdapter.clearItems()
                popularMovieAdapter.addItems(it.results)
                popularMovieAdapter.notifyDataSetChanged()
                binding.loadingPopular.hide()
            })
            popularMovie()
            println(Constants.LOCALE)
        }
    }


    private fun getNowPlayingMovies() {
        binding.loadingLookingNow.show()
        viewModel.apply {
            getObservedMoviesNowPlaying().observe(viewLifecycleOwner, { it ->
                nowPlayingMovieAdapter.clearItems()
                it.results.let {
                    nowPlayingMovieAdapter.addItems(it)
                    nowPlayingMovieAdapter.notifyDataSetChanged()
                    binding.loadingLookingNow.hide()
                }
            })
            nowPlayingMovie()
        }
    }

    private fun getUpComingMovies() {
        binding.loadingUpComing.show()
        viewModel.apply {
            getObservedMoviesUpComing().observe(viewLifecycleOwner, { it ->
                upComingMovieAdapter.clearItems()
                it.results.let {
                    upComingMovieAdapter.addItems(it)
                    upComingMovieAdapter.notifyDataSetChanged()
                    binding.loadingUpComing.hide()
                }
            })
            upComingMovie()
        }
    }


}