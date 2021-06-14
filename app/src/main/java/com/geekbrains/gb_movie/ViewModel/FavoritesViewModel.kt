package com.geekbrains.gb_movie.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.gb_movie.App
import com.geekbrains.gb_movie.Repository.AppState
import com.geekbrains.gb_movie.Repository.FavoriteRepository
import com.geekbrains.gb_movie.Repository.FavoriteRepositoryImpl

class FavoritesViewModel(
    val favoriteLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl(App.getFavoriteDao())
    ) : ViewModel() {

        fun getAllFavorite() {
            favoriteLiveData.value = AppState.Loading
            favoriteLiveData.value = AppState.Success(favoriteRepository.getAllFavorite())
        }

}