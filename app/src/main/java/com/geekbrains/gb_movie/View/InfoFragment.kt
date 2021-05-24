package com.geekbrains.gb_movie.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.gb_movie.Repository.Adapters.MovieFragmentDirections
import com.geekbrains.gb_movie.ViewModel.InfoViewModel
import com.geekbrains.gb_movie.databinding.InfoFragmentBinding

class InfoFragment : Fragment(){
    private val infoViewModel: InfoViewModel by lazy { ViewModelProvider(this).get(InfoViewModel::class.java) }
    private var _binding :InfoFragmentBinding?= null
    private val binding:InfoFragmentBinding get() =_binding!!
    private val movieFragmentDirections = MovieFragmentDirections

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        _binding = InfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieFragmentDirections.ge
    }
}