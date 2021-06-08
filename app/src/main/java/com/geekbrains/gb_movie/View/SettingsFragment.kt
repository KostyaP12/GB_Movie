package com.geekbrains.gb_movie.View

import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.ViewModel.SettingsViewModel
import com.geekbrains.gb_movie.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    private val settingViewModel: SettingsViewModel = SettingsViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
            : View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAdultCheckBoxValue()
        binding.adultCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            saveAdultCheckBoxValue(isChecked)
        }
    }

    private fun saveAdultCheckBoxValue(boolean: Boolean) {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(Constants.SP_ADULT_KEY, boolean)
                apply()
            }
        }
    }

    private fun showAdultCheckBoxValue() {
        activity?.let {
            binding.adultCheckbox.isChecked = it.getPreferences(Context.MODE_PRIVATE)
                    .getBoolean(Constants.SP_ADULT_KEY, false)
        }
    }
}