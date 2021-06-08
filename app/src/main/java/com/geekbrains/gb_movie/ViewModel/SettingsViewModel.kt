package com.geekbrains.gb_movie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel() : ViewModel() {

    fun adultCheckBoxChecked() = setValueSP(true)
    fun adultCheckBoxNotChecked() = setValueSP(false)

    private fun setValueSP(adultCheckBox: Boolean) {
        println(adultCheckBox)
    }
}