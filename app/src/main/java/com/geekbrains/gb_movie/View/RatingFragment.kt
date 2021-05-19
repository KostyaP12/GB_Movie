package com.geekbrains.gb_movie.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.ViewModel.RatingViewModel
import com.geekbrains.gb_movie.databinding.RatingFragmentBinding

class RatingFragment : Fragment() {

    private lateinit var binding: RatingFragmentBinding
    companion object {
        fun newInstance() = RatingFragment()
    }

    private lateinit var viewModel: RatingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rating_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RatingViewModel::class.java)
        // TODO: Use the ViewModel
    }
}