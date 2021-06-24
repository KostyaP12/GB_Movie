package com.geekbrains.gb_movie.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.Receivers.MyBroadcastReceiver
import com.geekbrains.gb_movie.Repository.Adapters.FavoriteAdapter
import com.geekbrains.gb_movie.Repository.Adapters.OnItemViewClickListener
import com.geekbrains.gb_movie.Repository.AppState
import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.ViewModel.FavoritesViewModel
import com.geekbrains.gb_movie.databinding.FavoriteFragmentBinding

class FavoriteFragment : Fragment() {

    private var favoritesViewModel: FavoritesViewModel = FavoritesViewModel()
    private lateinit var binding: FavoriteFragmentBinding
    private var bundle: Bundle = Bundle()
    private var myBroadcastReceiver: MyBroadcastReceiver = MyBroadcastReceiver()
    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter(onItemViewClickListener = object : OnItemViewClickListener {
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
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        doInitialization()
        super.onViewCreated(view, savedInstanceState)

    }

    private fun doInitialization() {
        when (myBroadcastReceiver.checkInternet(requireContext())) {
            false -> {
                Toast.makeText(requireContext(), "Нет соединения с интернетом", Toast.LENGTH_LONG).show()
            }
            true -> {

                binding.favoriteRecycler.apply {
                    adapter = favoriteAdapter
                    layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }
                favoritesViewModel.favoriteLiveData.observe(viewLifecycleOwner, {
                    renderData(it)
                })
                    favoriteAdapter.notifyDataSetChanged()

                favoritesViewModel.getAllFavorite()
            }
        }
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                favoriteAdapter.setData(appState.movieData)
            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
                favoritesViewModel.getAllFavorite()
            }
        }
    }
}