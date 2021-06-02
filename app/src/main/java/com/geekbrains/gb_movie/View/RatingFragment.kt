package com.geekbrains.gb_movie.View

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Receivers.MyBroadcastReceiver
import com.geekbrains.gb_movie.Repository.Adapters.HorizontalRecyclerAdapter
import com.geekbrains.gb_movie.Repository.Adapters.OnItemViewClickListener
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Model.MovieResponse
import com.geekbrains.gb_movie.Services.TopRatingService
import com.geekbrains.gb_movie.ViewModel.RatingViewModel
import com.geekbrains.gb_movie.databinding.MainFragmentBinding
import com.geekbrains.gb_movie.databinding.RatingFragmentBinding

class RatingFragment : Fragment() {

    private var _binding: RatingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RatingViewModel
    private lateinit var receiver: MyBroadcastReceiver
    private var bundle: Bundle = Bundle()
    private val movieReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val movieResponse = intent?.getSerializableExtra("movieResponse") as MovieResponse
            topRatingMovieAdapter.apply {
                clearItems()
                addItems(movieResponse.results)
                notifyDataSetChanged()
            }
        }
    }

    private val topRatingMovieAdapter by lazy {
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
            savedInstanceState: Bundle?):
            View? {_binding = RatingFragmentBinding.inflate(inflater, container, false)
            initialization()
        context?.startService(Intent(context, TopRatingService::class.java))
        return binding.root
    }

    private fun initialization() {
        binding.loadingTopRating.visibility = View.GONE
        binding.ratingRecycler.adapter = topRatingMovieAdapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                    .registerReceiver(movieReceiver, IntentFilter("INTENT FILTER"))
        }
    }

}